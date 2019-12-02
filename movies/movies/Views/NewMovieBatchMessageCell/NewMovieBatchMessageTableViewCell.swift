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
    @IBOutlet weak var messageLabel: UILabel!

    static let viewIdentifier: String = "NewMovieBatchMessageTableViewCell"

    override func awakeFromNib() {
        super.awakeFromNib()

        NotificationCenter.default.addObserver(self, selector: #selector(didStartedDownloading), name: .didStartedDownloading, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(didReceivedAnError), name: .didReceivedAnError, object: nil)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

    @objc func didStartedDownloading() {
        self.loadingIndicator.isHidden = false
        self.loadingIndicator.startAnimating()
        self.messageLabel.text = Constants.FETCHING_NEW_MOVIES_MESSAGE
        self.backgroundColor = nil
    }

    @objc func didReceivedAnError() {
        self.loadingIndicator.isHidden = true
        self.messageLabel.text = Constants.FETCHING_NEW_MOVIES_ERROR_MESSAGE
        self.backgroundColor = UIColor(named: "New_Movie_Batch_Message_Cell_Error_Color")
    }
}
