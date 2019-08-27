//
//  HomeViewModel.swift
//  Movs
//
//  Created by Eduardo Pereira on 26/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class HomeViewModel:NSObject{
    var dataAcess:DataAcess
    var data:[CollectionCellViewModel] = []
    var displayPage:(()->Void)?
    var didSelect:((CollectionCellViewModel)->Void)?
    var uiHandler:(()->Void)?
    var backgroundImage = UIImage(named: "black_background")
   // private var cellSize:CGSize
    init(dataAcess:DataAcess,uiHandler:(()->Void)? = nil) {
        self.dataAcess = dataAcess
        self.uiHandler = uiHandler
        super.init()
        dataAcess.getMovies {[weak self] (result) in

            //Todo:Return could not load movies
            self?.appendMovies(movies: result)
        }
    }
    func appendMovies(movies:[Movie]) {
        movies.forEach { (movie) in
            let model = CollectionCellViewModel(movie: movie, dataAcess: dataAcess)
            self.data.append(model)
        }
        uiHandler?()
    }
    
}

extension HomeViewModel:UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return data.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: MainMovieCollectionViewCell.reuseIdentifier, for: indexPath) as? MainMovieCollectionViewCell else{
            return MainMovieCollectionViewCell()
        }
        cell.setUpCell(movie: data[indexPath.item])
        return cell
    }
    
}
extension HomeViewModel:UICollectionViewDelegate,UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        //TODO: review
        self.didSelect?(data[indexPath.item])
    }
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        if indexPath.item == data.count - 3{
            //paginação
            uiHandler?()
        }
    }
//    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
//        return cellSize
//    }
}
