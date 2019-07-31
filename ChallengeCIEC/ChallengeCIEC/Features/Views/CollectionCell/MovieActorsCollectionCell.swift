//
//  MovieActorsCollectionCell.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 30/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import UIKit

class MovieActorsCollectionCell: UICollectionViewCell {

    @IBOutlet weak var imagePhoto: UIImageView!
    @IBOutlet weak var labelActor: UILabel!
    @IBOutlet weak var labelCharacter: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    func setup(cast: Cast?) {
        
        guard let c = cast else { return }
        
        self.labelActor.text = c.name
        self.labelCharacter.text = c.character
        
        self.serviceImage(image: c.profilePath)
    }
}

extension MovieActorsCollectionCell {
    
    private func serviceImage(image: String?) {
        guard var img = image else { return self.imagePhoto.backgroundColor = .lightGray }
        
        img.remove(at: img.startIndex)
        
        let service = TheMovieDBService()
        service.getImage(imagePath: img) { (image, error) in
            if let _ = error {
                self.imagePhoto.backgroundColor = .lightGray
            } else {
                if let img = image {
                    self.imagePhoto.image = img
                } else {
                    self.imagePhoto.backgroundColor = .lightGray
                }
            }
        }
    }
}
