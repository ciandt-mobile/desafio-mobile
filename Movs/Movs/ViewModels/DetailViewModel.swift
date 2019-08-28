//
//  DetailViewModel.swift
//  Movs
//
//  Created by Eduardo Pereira on 27/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class DetailViewModel:NSObject {
    private let model:Movie
    //let genre:NSAttributedString
    //let trailerPath:String
    var backgroundImage = UIImage(named: "black_background")
    var backdropImage:UIImage?
    let overview:NSAttributedString
    let title = NSMutableAttributedString()
    var uiHandler:(()->Void)?
    let dataAcess:DataAcess
    var genre = NSMutableAttributedString()
    var data:[CastCellViewModel] = []
    //artist
    
    init(model:Movie,dataAcess:DataAcess,uiHandler:(()->Void)? = nil) {
        self.model = model
        self.dataAcess = dataAcess
        self.uiHandler = uiHandler
        self.overview = NSAttributedString(string: model.overview ?? "", attributes: Typography.description(Color.white, nil).attributes())
        self.title.append(NSAttributedString(string:" \(model.title ?? "") ", attributes: Typography.title(Color.white, 19).attributes()))
        
        title.append(NSAttributedString(string: String(model.release_date?.split(separator: "-").first ?? ""), attributes: Typography.description(Color.white, 17).attributes()))
       super.init()
        
        
        dataAcess.getDuration(id: model.id) { [weak self](duration) in
            self?.genre.append(NSAttributedString(string: "\(duration ?? 0)m - ", attributes: Typography.description(Color.white, nil).attributes()))
            self?.getGenre()
           
            self?.uiHandler?()
        }
        dataAcess.getCast(id: String(model.id)) { [weak self](cast) in
            guard let self = self else{
                return
            }
            cast.forEach({ (actor) in
                
                self.data.append(CastCellViewModel(cast: actor, dataAcess: dataAcess, uiHandler: self.uiHandler))
            })
        }
        getImage()
        
       
        
        
    }
    func requestYoutube(){
        dataAcess.requestYoutube(id: String(model.id))
    }
    func configureNavBar(navController:UINavigationController?){
        navController?.navigationBar.setBackgroundImage(UIImage(), for: .default)
        navController?.navigationBar.shadowImage = UIImage()
        navController?.navigationBar.isTranslucent = true
        navController?.view.backgroundColor = .clear
        navController?.navigationBar.tintColor = Color.oldPaper

        
    }
    private func getImage(){
        if let backdropPath = model.backdrop_path{
            dataAcess.getImage(path: backdropPath, width: 1280) { [weak self](image) in
                self?.backdropImage = image
                self?.uiHandler?()
            }
        }
    }
    private func getGenre(){
        if let genreIds = model.genre_ids{
            var resultString:[String] = []
            for id in genreIds{
                let genre = dataAcess.genres.first { (genre) -> Bool in
                    return genre.id == id
                }
                resultString.append(genre?.name ?? "Not found")
            }
            let string = NSAttributedString(string: resultString.joined(separator: ", "), attributes: Typography.description(Color.white, nil).attributes())
            self.genre.append(string)
        }
    }


}
extension DetailViewModel:UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return data.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CastCellView.reuseIdentifier, for: indexPath) as? CastCellView else{
            return CastCellView()
        }
        cell.setUpCell(cast: data[indexPath.item])
        return cell
    }
    
}
extension DetailViewModel:UICollectionViewDelegate{

}
