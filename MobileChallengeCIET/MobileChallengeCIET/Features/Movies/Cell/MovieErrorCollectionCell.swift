//
//  MovieErrorCollectionCell.swift
//  MobileChallengeCIET
//
//  Created by Guilherme C Rosa on 22/07/19.
//  Copyright © 2019 Guilherme C Rosa. All rights reserved.
//

import UIKit

class MovieErrorCollectionCell: UICollectionViewCell {

    @IBOutlet weak var labelError: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    func setup(error: String) {
        self.labelError.text = error
    }
}
