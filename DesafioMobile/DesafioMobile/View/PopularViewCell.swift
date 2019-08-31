//
//  PopularViewCell.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Foundation
import SnapKit
import UIKit

//MARK: - The cell basic configuration and visual elements
class PopularViewCell: UICollectionViewCell{
    
    static let reuseIdentifier = "PopularCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    private lazy var imageView: UIImageView = {
        let image = UIImageView()
        return image
    }()
    
    private lazy var verticalContainer: UIStackView = {
        let view = UIStackView(frame: .zero)
        view.axis = .vertical
        view.spacing = 5
        return view
    }()
    
    private lazy var horizonalContainer: UIStackView = {
        let view = UIStackView(frame: .zero)
        view.axis = .horizontal
        view.spacing = 8
        return view
    }()
    
    private lazy var nameLabel: UILabel = {
        let label = UILabel()
        label.textColor = UsedColors.gold.color
        label.adjustsFontSizeToFitWidth = true
        label.numberOfLines = 0
        return label
    }()
    
    private lazy var yearLabel: UILabel = {
        let label = UILabel()
        label.textColor = UsedColors.gold.color
        label.backgroundColor = UsedColors.black.color
        label.adjustsFontSizeToFitWidth = true
        label.textAlignment = .center
        label.numberOfLines = 1
        return label
    }()
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

//MARK: - Feeds the data to the elements
extension PopularViewCell {
    func configure(withViewModel viewModel: PresentableMovieInterface){
        nameLabel.text = viewModel.name
        imageView.image = viewModel.bannerImage
        yearLabel.text = viewModel.date
    }
}

//MARK: - Extension to define the cell constraints
extension PopularViewCell: CodeView{
    func buildViewHierarchy() {
        verticalContainer.addSubview(imageView)
        verticalContainer.addSubview(nameLabel)
        imageView.addSubview(yearLabel)
        addSubview(verticalContainer)
    }
    
    func setupConstrains() {
        
        verticalContainer.snp.makeConstraints { (make) in
            make.top.bottom.equalToSuperview()
            make.left.equalToSuperview().offset(5)
            make.right.equalToSuperview().inset(5)
        }
        
        imageView.snp.makeConstraints { (make) in
            make.top.equalToSuperview().offset(5)
            make.right.equalToSuperview().inset(5)
            make.left.equalToSuperview().offset(5)
            make.height.equalToSuperview().multipliedBy(0.7)
        }
        
        nameLabel.snp.makeConstraints { (make) in
            make.left.bottom.equalToSuperview()
            make.left.right.equalToSuperview()
            make.top.equalTo(imageView.snp.bottom)
        }
        
        yearLabel.snp.makeConstraints { (make) in
            make.left.equalToSuperview().offset(10)
            make.right.equalToSuperview().inset(10)
            make.bottom.equalToSuperview().inset(10)
            make.height.equalToSuperview().multipliedBy(0.10)
        }
        
    }
    
    func setupAdditionalConfiguration() {
        backgroundColor = UsedColors.black.color
    }
}

