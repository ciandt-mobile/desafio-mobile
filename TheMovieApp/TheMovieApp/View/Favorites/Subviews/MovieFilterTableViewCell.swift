//
//  MovieFilterTableViewCell.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 01/07/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import UIKit

class MovieFilterTableViewCell: UITableViewCell {

    
    @IBOutlet weak var filterNameLabel: UILabel!
    @IBOutlet weak var checkImageView: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        setupImageView()
    }

    private func setupImageView() {
        checkImageView.layer.cornerRadius = checkImageView.frame.height / 2
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    public func setTitle(_ text:String) {
        filterNameLabel.text = text
    }
    
    public func setCheckImage(_ isSelected:Bool) {
        checkImageView.backgroundColor = isSelected ? Colors.yellow : Colors.clear
    }
}
