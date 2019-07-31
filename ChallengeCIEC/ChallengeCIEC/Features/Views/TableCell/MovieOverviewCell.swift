//
//  MovieOverviewCell.swift
//  ChallengeCIEC
//
//  Created by Guilherme C Rosa on 27/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import UIKit

class MovieOverviewCell: UITableViewCell {
    
    @IBOutlet weak var labelOverview: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setup(movie: MovieDetail?) {
        if let m = movie, let overview = m.overview {
            self.labelOverview.text = overview
        }
    }
}
