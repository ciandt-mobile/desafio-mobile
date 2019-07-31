//
//  MovieDetailStatusCell.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import UIKit

class MovieDetailStatusCell: UITableViewCell {

    @IBOutlet weak var loader: UIActivityIndicatorView!
    @IBOutlet weak var labelStatus: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setup(status: MovieDetailTableBuilderState, error: String?) {
        
        if status == .Loading {
            self.loader.isHidden = false
            self.labelStatus.isHidden = true
        } else {
            self.loader.isHidden = true
            self.labelStatus.isHidden = false
        }
        
        if let text = error {
            self.labelStatus.text = text
        }
    }
    
}
