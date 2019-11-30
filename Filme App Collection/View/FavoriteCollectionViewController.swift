//
//  FavoriteCollectionViewController.swift
//  Filme App Collection
//
//  Created by Miguel Fernandes Lopes on 27/11/19.
//  Copyright © 2019 Miguel Fernandes Lopes. All rights reserved.
//

import UIKit
import Kingfisher
import CoreData

let reuseIdentifier2 = "celula"

class FavoriteCollectionViewController: UICollectionViewController {

    private var context: NSManagedObjectContext!
    private var saveFav: [NSManagedObject] = []
    private var loadRetorno: [String] = []
    private var loadGenre: [String] = []

    @IBOutlet private var referFavoriteView2: UICollectionView!

    override func viewDidLoad() {
        super.viewDidLoad()
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        context = appDelegate.persistentContainer.viewContext

        self.collectionView!.register(UICollectionViewCell.self, forCellWithReuseIdentifier: reuseIdentifier2)

        self.loadSave()
    }

    override func viewDidAppear(_ animated: Bool) {
        self.loadSave()
    }

    func loadSave() {
        let requisicao = NSFetchRequest<NSFetchRequestResult>(entityName: "Favorite")
        do {
            let loadRequisicao = try context.fetch(requisicao)
            self.saveFav = loadRequisicao as! [NSManagedObject]
            self.referFavoriteView2?.reloadData()
        } catch let erro {
            print("Erro ao recuperar dados: \(erro.localizedDescription)")
        }
    }

    // MARK: UICollectionViewDataSource
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        var numOfSections: Int = 0
        if self.saveFav.count > 0 {
            collectionView.indicatorStyle = .default
            numOfSections            = 1
            collectionView.backgroundView = nil
        } else {
            let noDataLabel: UILabel  = UILabel(frame: CGRect(x: 0, y: 0, width: collectionView.bounds.size.width, height: collectionView.bounds.size.height))
            noDataLabel.text          = "No favorites saved"
            noDataLabel.textColor     = UIColor.white
            noDataLabel.textAlignment = .center
            collectionView.backgroundView  = noDataLabel
            collectionView.indicatorStyle  = .default
        }
        return numOfSections
    }

    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.saveFav.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "celulaReusoFavorito", for: indexPath) as! FavoriteCollectionViewCell
        
        //Load CoreData
        let result = self.saveFav[indexPath.row]
        let titulo = result.value(forKey: "title") as? String
        let imagem = result.value(forKey: "imagem") as! String

        let selecionaImagem = ImageResource(downloadURL: URL(string: "https://image.tmdb.org/t/p/w500/" +  imagem)!, cacheKey: imagem)
        cell.setupCell(title: titulo!, image: selecionaImagem)
        
        cell.index = indexPath
        cell.delegate = self
        return cell
    }
}

extension FavoriteCollectionViewController: CollectionProtocol {
    func deleteData(indx: Int) {
        let indice = self.saveFav[indx]
        self.context.delete(indice)
        do {
            try context.save()
            self.loadSave()
            self.referFavoriteView2?.reloadData()
        } catch let erro {
            print("Dados não foram salvos: \(erro.localizedDescription)")
        }
    }
}
