//
//  NewMovieBatchMessageTableViewCell.swift
//  movies
//
//  Created by Arthur Silva on 12/1/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import UIKit

class NewMovieBatchMessageTableViewCell: UITableViewCell {

    @IBOutlet weak var loadingIndicator: UIActivityIndicatorView!

    static let viewIdentifier: String = "NewMovieBatchMessageTableViewCell"

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
}
