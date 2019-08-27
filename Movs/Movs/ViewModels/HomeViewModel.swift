//
//  HomeViewModel.swift
//  Movs
//
//  Created by Eduardo Pereira on 26/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class HomeViewModel:NSObject{
    var page = 1
    var dataAcess:DataAcess
    var data:[CollectionCellViewModel] = []
    var displayPage:(()->Void)?
    var didSelect:((CollectionCellViewModel)->Void)?
    var uiHandler:(()->Void)?
    var backgroundImage = UIImage(named: "black_background")
    //Could use NSlocalizable string to have more languages
    var segmentItens = ["Upcoming","Popular"]
   // private var cellSize:CGSize
    init(dataAcess:DataAcess,uiHandler:(()->Void)? = nil) {
        self.dataAcess = dataAcess
        self.uiHandler = uiHandler
        super.init()
        dataAcess.getMovies(request: Request.popular, page: page) { [weak self](result) in
            self?.appendMovies(movies: result)
        }
    }
    func appendMovies(movies:[Movie]) {
        movies.forEach { (movie) in
            let model = CollectionCellViewModel(movie: movie, dataAcess: dataAcess, uiHandler: uiHandler)
            self.data.append(model)
        }
        uiHandler?()
    }
    func configureNavBar(navController:UINavigationController?){
        navController?.navigationBar.setBackgroundImage(UIImage(), for: .default)
        navController?.navigationBar.shadowImage = UIImage()
        navController?.navigationBar.isTranslucent = true
        navController?.view.backgroundColor = .clear
    }
    func resetPage(){
        self.page = 1
    }
    func changeData(request:Request){
        resetPage()
        dataAcess.getMovies(request: request, page: self.page) { [weak self](results) in
            self?.data = []
            self?.appendMovies(movies: results)
        }
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
}
