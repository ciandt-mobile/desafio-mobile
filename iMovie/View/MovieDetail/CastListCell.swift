//
//  CastListCell.swift
//  iMovie
//
//  Created by João Tocilo on 03/08/19.
//  Copyright © 2019 Fulltime. All rights reserved.
//

import Foundation
import UIKit

class CastListCell: UICollectionViewCell {
    
    var cast : Cast? {
        didSet {
            if let currentCast = cast {
                labelName.text = currentCast.name
                if let image = currentCast.profilePath {
                    thumbnail.pin_setImage(from: URL(string: "\(Constants.BASE_URL_IMG)w500\(image)"))
                } else {
                    thumbnail.image = UIImage(named: "not-found")
                    thumbnail.contentMode = UIView.ContentMode.scaleToFill
                }
            }
        }
    }
    
    private let thumbnail : UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = UIView.ContentMode.scaleAspectFit
        return imageView
    }()
    
    private let labelName : UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 10)
        label.numberOfLines = 3
        label.textAlignment = .center
        label.textColor = .white
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        let castNameContainer = UIView()
        castNameContainer.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 0.6)
        
        addSubview(thumbnail)
        thumbnail.snp.makeConstraints { (make) in
            make.top.centerX.equalToSuperview()
            make.width.height.equalToSuperview()
        }
        
        addSubview(castNameContainer)
        castNameContainer.snp.makeConstraints { (make) in
            make.left.bottom.right.equalToSuperview()
        }
        
        castNameContainer.addSubview(labelName)
        labelName.snp.makeConstraints { (make) in
            make.left.bottom.right.top.equalToSuperview()
        }
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
