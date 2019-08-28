//
//  HomeViewModel.swift
//  Movs
//
//  Created by Eduardo Pereira on 26/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class HomeViewModel:NSObject{
    private var page = 1
    var dataAcess:DataAcess
    private var currentRequest = Request.popular
    var data:[MovieCellViewModel] = []
    var displayPage:(()->Void)?
    var didSelect:((MovieCellViewModel)->Void)?
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
            let model = MovieCellViewModel(movie: movie, dataAcess: dataAcess, uiHandler: uiHandler)
            self.data.append(model)
        }
        uiHandler?()
    }
    private func resetPage(){
        self.page = 1
    }
    func configureNavBar(navController:UINavigationController?){
        navController?.navigationBar.setBackgroundImage(UIImage(), for: .default)
        navController?.navigationBar.shadowImage = UIImage()
        navController?.navigationBar.isTranslucent = true
        navController?.view.backgroundColor = .clear
        
    }
    func changeData(request:Request){
        resetPage()
        currentRequest = request
        dataAcess.getMovies(request: request, page: self.page) { [weak self](results) in
            self?.data = []
            self?.appendMovies(movies: results)
        }
    }
    private func addPage(){
        self.page += 1
        dataAcess.getMovies(request: currentRequest, page: self.page) { [weak self](results) in
            self?.appendMovies(movies: results)
        }
    }
    
}

extension HomeViewModel:UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return data.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: MovieCollectionViewCell.reuseIdentifier, for: indexPath) as? MovieCollectionViewCell else{
            return MovieCollectionViewCell()
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
            self.addPage()
        }
    }
}
