//
//  CastViewCell.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/29/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Foundation
import UIKit
import SnapKit


//MARK: - The cell basic configuration and visual elements
class CastViewCell: UICollectionViewCell{
    
    static let reuseIdentifier = "CastCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    private lazy var imageView: UIImageView = {
        let image = UIImageView()
        return image
    }()
    
    
    private lazy var nameLabel: UILabel = {
        let label = UILabel()
        label.textColor = UsedColors.gold.color
        label.adjustsFontSizeToFitWidth = true
        label.numberOfLines = 0
        return label
    }()
    
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

//MARK: - Feeds the data to the elements
extension CastViewCell {
    func configure(withViewModel actor: Actor, actorImage: UIImage){
        nameLabel.text = actor.name
        imageView.image = actorImage
    }
}

//MARK: - Extension to define the cell constraints
extension CastViewCell: CodeView{
    func buildViewHierarchy() {
        addSubview(imageView)
        addSubview(nameLabel)
    }
    
    func setupConstrains() {
        
        imageView.snp.makeConstraints { (make) in
            make.top.equalToSuperview()
            make.right.left.equalToSuperview()
            make.height.equalToSuperview().multipliedBy(0.8)
        }
        
        nameLabel.snp.makeConstraints { (make) in
            make.left.right.bottom.equalToSuperview()
            make.height.equalToSuperview().multipliedBy(0.15)
        }
        
        
    }
    
    func setupAdditionalConfiguration() {
        backgroundColor = UsedColors.black.color
    }
}

