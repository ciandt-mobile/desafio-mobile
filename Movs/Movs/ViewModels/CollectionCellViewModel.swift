//
//  CollectionCellViewModel.swift
//  Movs
//
//  Created by Eduardo Pereira on 28/07/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit


class CollectionCellViewModel{
    var image:UIImage? = UIImage(named: "image_not_found")
    var title:NSAttributedString
    var release_data:NSAttributedString
    private let movie:Movie
    init(movie:Movie,dataAcess:DataAcess){
        self.title = NSAttributedString(string:  movie.title ?? "" , attributes: Typography.title(Color.black).attributes())
        self.release_data = NSAttributedString(string:  movie.release_date ?? "" , attributes: Typography.title(Color.black).attributes())
        self.movie = movie
        dataAcess.getImage(path: movie.poster_path ?? "") {[weak self] (image) in
            self?.image = image
        }
    }

    func getMovie()->Movie{
        return movie
    }

}
