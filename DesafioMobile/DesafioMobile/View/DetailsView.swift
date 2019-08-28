//
//  DetailView.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import UIKit
import SnapKit

//MARK: - The view basic configuration and visual elements
class DetailsView: UIView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    lazy var imageView: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.contentMode = .scaleToFill
        view.sizeToFit()
        return view
    }()
    
    
    lazy var titleLabel: UILabel = {
        let view = UILabel(frame: .zero)
        view.textAlignment = .center
        view.textColor = UsedColors.white.color
        view.numberOfLines = 0
        return view
    }()
    
    lazy var genresLabel: UILabel = {
        let view = UILabel(frame: .zero)
        view.textAlignment = .center
        view.textColor = UsedColors.white.color
        view.numberOfLines = 0
        return view
    }()
    
    lazy var verticalContainer: UIView = {
        let view = UIView(frame: .zero)
        view.backgroundColor = UsedColors.black.color
        return view
    }()
    
    lazy var descLabel: UILabel = {
        let view = UILabel(frame: .zero)
        view.adjustsFontSizeToFitWidth = true
        view.textColor = UsedColors.white.color
        view.numberOfLines = 0
        return view
    }()
    
}

//MARK: - Extension to define the cell constraints
extension DetailsView{
    func configure(detailedMovie: PresentableMovieInterface,genreNames: String){
        descLabel.text = detailedMovie.description
        genresLabel.text = genreNames
        titleLabel.text = detailedMovie.name
        imageView.image = detailedMovie.bannerImage
    }
}


//MARK: - Extension to define the cell constraints
extension DetailsView: CodeView{
    func buildViewHierarchy() {
        verticalContainer.addSubview(titleLabel)
        verticalContainer.addSubview(genresLabel)
        addSubview(verticalContainer)
        addSubview(imageView)
        addSubview(descLabel)
    }
    
    func setupConstrains() {
        imageView.snp.makeConstraints { (make) in
            make.right.equalToSuperview()
            make.left.equalToSuperview()
            make.top.equalToSuperview()
            make.height.equalToSuperview().multipliedBy(0.5)
        }
        
        titleLabel.snp.makeConstraints { (make) in
            make.left.equalToSuperview()
            make.right.equalToSuperview()
            make.height.equalToSuperview().multipliedBy(0.5)
            make.top.equalToSuperview().offset(2)
        }
        
        genresLabel.snp.makeConstraints { (make) in
            make.left.equalToSuperview()
            make.right.equalToSuperview()
            make.top.equalTo(titleLabel.snp.bottom)
            make.bottom.equalTo(verticalContainer.snp.bottom)
        }
        
        verticalContainer.snp.makeConstraints { (make) in
            make.width.equalToSuperview()
            make.left.equalToSuperview()
            make.top.equalTo(imageView.snp.bottom)
            make.height.equalToSuperview().multipliedBy(0.15)
        }
        
        
        descLabel.snp.makeConstraints { (make) in
            make.left.right.equalToSuperview().offset(5)
            make.top.equalTo(verticalContainer.snp.bottom)
            make.bottom.equalTo(snp.bottomMargin)
        }
    }
    
    func setupAdditionalConfiguration() {
        backgroundColor = UsedColors.black.color
    }
}
