//
//  DescricaoCollectionView.swift
//  Filme App Collection
//
//  Created by Miguel Fernandes Lopes on 27/11/19.
//  Copyright © 2019 Miguel Fernandes Lopes. All rights reserved.
//

import UIKit
import Kingfisher
import CoreData

class DescricaoViewController: UIViewController, UICollectionViewDelegate, UICollectionViewDataSource {

    @IBOutlet private weak var referView: UIView!
    @IBOutlet private weak var imagemDescricao: UIImageView!
    @IBOutlet private weak var tituloDescricao: UILabel!
    @IBOutlet private weak var generoDescricao: UILabel?
    @IBOutlet private weak var favoriteButton: UIButton!
    @IBOutlet private weak var anoEstreia: UILabel!
    @IBOutlet private weak var runtime: UILabel!
    @IBOutlet private weak var sinopse: UITextView!
    
    
    var dados_json = [Result]()
    private var genero_json = [Genero]()
    var genero_Filme2 = [Genre]()
    private var dados_cast = [FormatoCast]()
    private var cast = [Cast]()
    private var detailMovie = [FormatoDetailMovies]()
    private var generoSalvo: [String] = [""]
    private var arrayJoin: String = ""
    private var context: NSManagedObjectContext!
    private var loadCoreData: [NSManagedObject] = []
    private var favoriteSave = [Result]()
    var dadosID: [Int] = []
    var imagemDesc: ImageResource?
    private var imagemMainCast: ImageResource?

    @IBOutlet weak var collectionView: UICollectionView!
    
    override func viewWillAppear(_ animated: Bool) {
        funcGetMainCast()
        funcGetDetailFullMovie()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        //funcLoadView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        context = appDelegate.persistentContainer.viewContext
        funcLoadCoreData()
        self.referView.reloadInputViews()
    }
    
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.cast.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cellReusoCast", for: indexPath) as! DescricaoCollectionViewCell
        
        let selecionaImagem = ImageResource(downloadURL: URL(string: "https://image.tmdb.org/t/p/w500" + (cast[indexPath.row].profilePath ?? ""))!, cacheKey: (cast[indexPath.row].profilePath))
        if (cast[indexPath.row].profilePath != nil) {
            cell.ImageCast.kf.setImage(with: selecionaImagem)
        }
        
        return cell
    }
    
    
    func funcGetMainCast() {
        guard let typicodeUrl = URL(string: "https://api.themoviedb.org/3/movie/" + (String(dados_json[0].id)) + "/credits?api_key=2164a2fbfffba318c500028e8631ffd9") else { return }
        URLSession.shared.dataTask(with: typicodeUrl) { (data, _, _) in
            guard let data = data else { return }
            do {
                let decoder = JSONDecoder()
                let cast2 = try decoder.decode(FormatoCast.self, from: data)
                DispatchQueue.main.async {
                    self.dados_cast = [cast2]
                    self.cast = cast2.cast
                    self.collectionView.reloadData()
                    self.referView.reloadInputViews()
                }
            } catch let err {
                print ("Erro no json da api get main cast", err)
            }
            }.resume()
    }
    
    
    func funcGetDetailFullMovie() {
        guard let typicodeUrl = URL(string: "https://api.themoviedb.org/3/movie/" + (String(dados_json[0].id)) + "?api_key=2164a2fbfffba318c500028e8631ffd9") else { return }
        URLSession.shared.dataTask(with: typicodeUrl) { (data, _, _) in
            guard let data = data else { return }
            do {
                let decoder = JSONDecoder()
                let movie = try decoder.decode(FormatoDetailMovies.self, from: data)
                DispatchQueue.main.async {
                    self.detailMovie = [movie]
                    self.funcLoadView()
                    self.referView.reloadInputViews()
                }
            } catch let err {
                print ("Erro no json da api detail full movies", err)
            }
            }.resume()
    }
    
    // Monta tela
    func funcLoadView() {
        imagemDescricao.kf.setImage(with: imagemDesc)
        sinopse.text = dados_json[0].overview
        tituloDescricao.text = dados_json[0].title
        
        if ((self.detailMovie[0].runtime) != nil) {
            self.runtime.text! = String(self.detailMovie[0].runtime!)
        }

        //Converter data - label ano
        let dateFormatterGet = DateFormatter()
        dateFormatterGet.dateFormat = "yyyy-MM-dd"
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy"
        let date = dateFormatterGet.date(from: String(self.detailMovie[0].releaseDate!))
        anoEstreia.text = dateFormatter.string(from: date!)
        
        //Monta array genero
        self.generoDescricao?.text = arrayJoin
        for gen in (self.detailMovie[0].genres) {
            generoSalvo.insert(gen.name, at: 0)
            arrayJoin = generoSalvo.joined(separator: ", ")
            arrayJoin.removeLast(2)
        }
        self.generoDescricao?.text = arrayJoin
            
        //Verifica se o filme esta salvo favorito
        for idCoreData in self.dadosID {
            if idCoreData == self.dados_json[0].id {
                favoriteButton.setImage(UIImage(named: "icon_love_selected"), for: .normal)
            }
        }
    }

    //Funcao get filmes favorito (CoreData)
    func funcLoadCoreData() {
        let requisicao = NSFetchRequest<NSFetchRequestResult>(entityName: "Favorite")
        do {
            let loadRequisicao = try context.fetch(requisicao)
            self.loadCoreData = loadRequisicao as! [NSManagedObject]
            for dados in self.loadCoreData {
                self.dadosID.append(dados.value(forKey: "id") as! Int)
                self.referView.reloadInputViews()
            }
        } catch let erro {
            print("Erro ao recuperar dados: \(erro.localizedDescription)")
        }
    }

    //Botao Favoritar
    @IBAction func favoriteButton(_ sender: Any) {
        self.verifID()
    }

    public func verifID() -> Bool {
        var existe = false
        for idCoreData in self.dadosID {
            if idCoreData == self.dados_json[0].id {
                existe = true
            }
        }
        if(existe) {
            funcRemoveFavorite()
        } else {
            funcSaveFavorite()
        }
        return existe
    }

    //Funcao para salvar filme no favorito (CoraData)
    func funcSaveFavorite() {
        let newSave = NSEntityDescription.insertNewObject(forEntityName: "Favorite", into: context)

        newSave.setValue(dados_json[0].title, forKey: "title")
        newSave.setValue(dados_json[0].id, forKey: "id")
        newSave.setValue(dados_json[0].posterPath, forKey: "imagem")

        do {
            try context.save()
            favoriteButton.setImage(UIImage(named: "icon_love_selected"), for: .normal)
        } catch let erro {
            print("Dados não foram salvos: \(erro.localizedDescription)")
        }
        self.funcLoadCoreData()
        self.referView.reloadInputViews()
    }

    //Funcao para remover filme do favorito (CoreData)
    func funcRemoveFavorite() {
        let fetchRequest: NSFetchRequest<Favorite> = Favorite.fetchRequest()
        fetchRequest.predicate = NSPredicate.init(format: "id==\(self.dados_json[0].id)")

        do {
            let objects = try context.fetch(fetchRequest)
            for object in objects {
                context.delete(object)
            }
            try context.save()
            favoriteButton.setImage(UIImage(named: "icon_love"), for: .normal)
            self.funcLoadCoreData()
            self.referView.reloadInputViews()
        } catch let erro {
            print("Dados não foram salvos: \(erro.localizedDescription)")
        }
    }
}
