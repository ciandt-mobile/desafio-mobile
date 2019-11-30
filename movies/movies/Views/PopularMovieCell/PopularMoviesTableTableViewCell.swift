//
//  PopularMoviesTableTableViewCell.swift
//  movies
//
//  Created by Arthur Silva on 11/30/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import UIKit

class PopularMoviesTableTableViewCell: UITableViewCell {

    @IBOutlet weak var moviePoster: UIImageView!
    @IBOutlet weak var movieTitle: UILabel!
    @IBOutlet weak var yearLabel: UILabel!
    @IBOutlet weak var voteAverageScoreLabel: UILabel!
    
    static let cellIdentifier: String = "PopularMoviesTableTableViewCell"

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
