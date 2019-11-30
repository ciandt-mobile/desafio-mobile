//
//  CollectionViewController.swift
//  Filme App Collection
//
//  Created by Miguel Fernandes Lopes on 27/11/19.
//  Copyright © 2019 Miguel Fernandes Lopes. All rights reserved.
//

import UIKit
import Kingfisher
import CoreData

class CollectionViewController: UICollectionViewController {

    private var dados_json = [FormatoJson]()
    private var resultado = [Result]()
    private var genero_Filme = [Genre]()
    private var dados_cast = [FormatoCast]()
    private var cast = [Cast]()
    private var context: NSManagedObjectContext!
    private var loadCoreData: [NSManagedObject] = []
    private var idVerificado: Int = 0
    private var dadosID: [Int] = []

    @IBOutlet private var referTableView: UICollectionView!
    @IBOutlet private weak var segmentControl: UISegmentedControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        context = appDelegate.persistentContainer.viewContext
        self.referTableView.reloadData()
        self.funcGeneroJson()
        self.funcGetMoviesUpcoming()
        //self.dados_json = Providers().funcGetMoviesPopular()
    }
  
    @IBAction func indexChanged(_ sender: Any) {
        switch segmentControl.selectedSegmentIndex
        {
        case 0:
            self.funcGetMoviesUpcoming()
        case 1:
            //self.dados_json = Providers().funcGetMoviesPopular()
            self.funcGetMoviesPopular()
        default:
            break
        }
    }
    
    // MARK: Get Movies Popular
    
    public func funcGetMoviesPopular() {
        guard let typicodeUrl = URL(string: "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=2164a2fbfffba318c500028e8631ffd9") else { return }

        URLSession.shared.dataTask(with: typicodeUrl) { (data, _, _) in
            guard let data = data else { return }
            do {
                let decoder = JSONDecoder()
                let json_full = try decoder.decode(FormatoJson.self, from: data)
                DispatchQueue.main.async {
                    self.dados_json = [json_full]
                    self.resultado = json_full.results
                    self.referTableView?.reloadData()
                }
            } catch let err {
                print ("Erro no json da api", err)
            }
            }.resume()
    }
    
    // MARK: Get Movies Upcoming
    
    func funcGetMoviesUpcoming() {
        guard let typicodeUrl = URL(string: "https://api.themoviedb.org/3/movie/upcoming?api_key=2164a2fbfffba318c500028e8631ffd9") else { return }

        URLSession.shared.dataTask(with: typicodeUrl) { (data, _, _) in
            guard let data = data else { return }
            do {
                let decoder = JSONDecoder()
                let json_full = try decoder.decode(FormatoJson.self, from: data)
                DispatchQueue.main.async {
                    self.dados_json = [json_full]
                    self.resultado = json_full.results
                    self.referTableView?.reloadData()
                }
            } catch let err {
                print ("Erro no json da api", err)
            }
            }.resume()
    }
    
    // MARK: Get Genero
    
    func funcGeneroJson() {
        guard let typicodeUrl = URL(string: "https://api.themoviedb.org/3/genre/movie/list?api_key=2164a2fbfffba318c500028e8631ffd9&language=en-US") else { return }

        URLSession.shared.dataTask(with: typicodeUrl) { (data, _, _) in
            guard let data = data else { return }
            do {
                let decoder = JSONDecoder()
                let json_full = try decoder.decode(Genero.self, from: data)
                DispatchQueue.main.async {
                    self.genero_Filme = json_full.genres
                    self.referTableView?.reloadData()
                }

            } catch let err {
                print ("Erro no json da api genero", err)
            }
            }.resume()
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "segueDescricao" {
            if let descricaoViewController = segue.destination as? DescricaoViewController {
                let cell = sender as! UICollectionViewCell
                let indexPath = self.collectionView!.indexPath(for: cell)
                let descricao = resultado[(indexPath?.row)!]

                let selecionaImagem = ImageResource(downloadURL: URL(string: "https://image.tmdb.org/t/p/w500/" +  resultado[(indexPath?.row)!].posterPath)!, cacheKey: resultado[(indexPath?.row)!].posterPath)
                
                descricaoViewController.imagemDesc = selecionaImagem
                descricaoViewController.dados_json = [descricao]
                descricaoViewController.genero_Filme2 = self.genero_Filme
            }
        }
    }

    // MARK: UICollectionViewDataSource
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        var numOfSections: Int = 0
        if self.resultado.count > 0 {
            collectionView.indicatorStyle = .default
            numOfSections            = 1
            collectionView.backgroundView = nil
        } else {
            let noDataLabel: UILabel  = UILabel(frame: CGRect(x: 0, y: 0, width: collectionView.bounds.size.width, height: collectionView.bounds.size.height))
            noDataLabel.text          = "Check your internet connection"
            noDataLabel.textColor     = UIColor.white
            noDataLabel.textAlignment = .center
            collectionView.backgroundView  = noDataLabel
            collectionView.indicatorStyle  = .default
        }
        return numOfSections
    }

    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return resultado.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        //let celulaReuso = "celulaReuso"
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "celulaReuso", for: indexPath) as? TelaCollectionViewCell else { return UICollectionViewCell() }
        
        let selecionaImagem = ImageResource(downloadURL: URL(string: "https://image.tmdb.org/t/p/w500/" +  resultado[indexPath.row].posterPath)!, cacheKey: resultado[indexPath.row].posterPath)
        cell.setupCell(title: resultado[indexPath.row].title, image: selecionaImagem, dateLancemto: resultado[indexPath.row].releaseDate)
        
        return cell
    }
}
