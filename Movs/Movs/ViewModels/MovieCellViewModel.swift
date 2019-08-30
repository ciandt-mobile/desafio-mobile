//
//  CollectionCellViewModel.swift
//  Movs
//
//  Created by Eduardo Pereira on 28/07/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit


class MovieCellViewModel{
    var image:UIImage?
    var title:NSAttributedString
    private let movie:Movie
    init(movie:Movie,dataAcess:DataAcess,uiHandler:(()->Void)?){
        let title = NSMutableAttributedString()
        title.append(NSAttributedString(string:  (movie.title ?? "None" ) + "\n" , attributes: Typography.title(Color.black, nil).attributes()))
        var dateString = "No Date"
        if let date = movie.release_date {
            var substring = date.split(separator: "-")
            substring.reverse()
            dateString = substring.joined(separator: "-")
        }
        title.append(NSAttributedString(string: dateString, attributes: Typography.title(Color.scarlet, nil).attributes()))
       
        self.title = title
        self.movie = movie
        dataAcess.getImage(path: movie.poster_path ?? "", width: 500) {[weak self] (image) in
            if let image = image{
                self?.image = image
            }else{
                self?.image = UIImage(named: "image_not_found")
            }
            
            uiHandler?()
        }
    }

    func getMovie()->Movie{
        return movie
    }

}
