//
//  DetailViewModel.swift
//  Movs
//
//  Created by Eduardo Pereira on 27/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class DetailViewModel {
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
    //artist
    
    init(model:Movie,dataAcess:DataAcess,uiHandler:(()->Void)? = nil) {
        self.model = model
        self.dataAcess = dataAcess
        self.uiHandler = uiHandler
        self.overview = NSAttributedString(string: model.overview ?? "", attributes: Typography.description(Color.white, nil).attributes())
        self.title.append(NSAttributedString(string:" \(model.title ?? "") ", attributes: Typography.title(Color.white, 19).attributes()))
        
        title.append(NSAttributedString(string: String(model.release_date?.split(separator: "-").first ?? ""), attributes: Typography.description(Color.white, 17).attributes()))
       
        
            getImage()
        dataAcess.getDuration(id: model.id) { [weak self](duration) in
            self?.genre.append(NSAttributedString(string: "\(duration ?? 0)m - ", attributes: Typography.description(Color.white, nil).attributes()))
            self?.getGenre()
            self?.uiHandler?()
        }
        
       
        
        
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
