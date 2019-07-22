//
//  CastCollectionViewCell.swift
//  MovieApp
//
//  Created by Michele de Olivio Corrêa on 20/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import Foundation
import UIKit
class CastCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var uiActorName: UILabel?
    @IBOutlet weak var uiActorImage: UIImageView?
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    public func setup(actor: Movie.MovieCast) {
        uiActorName?.text = actor.name
        uiActorImage?.image = UIImage(named: "AppIcon")
        uiActorImage?.downloaded(from: actor.profileURL)
    }
    
}
