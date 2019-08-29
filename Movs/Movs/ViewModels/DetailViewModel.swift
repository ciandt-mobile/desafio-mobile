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
    var youtubeURL:String?
    //artist
    
    init(model:Movie,dataAcess:DataAcess,uiHandler:(()->Void)? = nil) {
        self.model = model
        self.dataAcess = dataAcess
        self.uiHandler = uiHandler
        self.overview = NSAttributedString(string: model.overview ?? "No overview", attributes: Typography.description(Color.white, nil).attributes())
        self.title.append(NSAttributedString(string:" \(model.title ?? "No title") ", attributes: Typography.title(Color.white, 19).attributes()))
        
        title.append(NSAttributedString(string: String(model.release_date?.split(separator: "-").first ?? "No data"), attributes: Typography.description(Color.white, 17).attributes()))
       super.init()
        
        
        dataAcess.getDetail(id: model.id) { [weak self](movie) in
            self?.genre.append(NSAttributedString(string: "\(movie?.runtime ?? 0)m - ", attributes: Typography.description(Color.white, nil).attributes()))
            if let video = movie?.videos?.results.first?.key{
                self?.youtubeURL = "https://www.youtube.com/watch?v=\(video)"
            }
            guard let genres = movie?.genres else{
                return
            }
            var resultString:[String] = []
            for genre in genres{
              resultString.append(genre.name)
            }
            let string = NSAttributedString(string: resultString.joined(separator: ", "), attributes: Typography.description(Color.white, nil).attributes())
            self?.genre.append(string)
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
        guard let url = URL(string:self.youtubeURL!)else{
            return
        }
        DispatchQueue.main.async {
            if UIApplication.shared.canOpenURL(url) {
                UIApplication.shared.open(url, options: [:], completionHandler: nil)
            }
        }
    }
    func configureNavBar(controller:UIViewController){
        controller.navigationController?.navigationBar.isHidden = true

        
    }

    private func getImage(){
        dataAcess.getImage(path: model.backdrop_path ?? "", width: 1280) { [weak self](image) in
            if let image = image{
                self?.backdropImage = image
                
            }else{
                self?.backdropImage = UIImage(named: "image_not_found")
            }
           self?.uiHandler?()
        }
    }
    @objc
    func back(){
        
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
