//
//  FavoriteCollectionViewCell.swift
//  Filme App Collection
//
//  Created by Miguel Fernandes Lopes on 27/11/19.
//  Copyright © 2019 Miguel Fernandes Lopes. All rights reserved.
//

import UIKit
import CoreData
import Kingfisher

protocol CollectionProtocol {
    func deleteData(indx: Int)
}

class FavoriteCollectionViewCell: UICollectionViewCell {

    @IBOutlet private weak var imgFavorite: UIImageView!
    @IBOutlet private weak var titleFavorite: UILabel!

    var delegate: CollectionProtocol?
    var index: IndexPath?

    @IBAction func btnRemoveFavorite(_ sender: Any) {
        delegate?.deleteData(indx: (index?.row)!)
    }
    
    func setupCell(title: String, image: Resource){
        titleFavorite.text = title
        imgFavorite.kf.setImage(with: image)
    }
}
