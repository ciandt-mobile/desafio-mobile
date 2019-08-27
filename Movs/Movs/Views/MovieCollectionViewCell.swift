//
//  MainMovieCollectionViewCell.swift
//  Movs
//
//  Created by Eduardo Pereira on 28/07/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit
@IBDesignable

class MovieCollectionViewCell: UICollectionViewCell {
    static let reuseIdentifier = "MovieCollectionCell"
    
    let posterImage: UIImageView = {
        let view = UIImageView(image: nil)
        view.clipsToBounds = true
        view.contentMode = UIView.ContentMode.scaleAspectFill
        return view
    }()
    let titleLabel:UILabel = {
        let label = UILabel()
        label.backgroundColor = .white
        label.layer.borderWidth = 0
        label.numberOfLines = 0
        label.textAlignment = NSTextAlignment.center
        return label
    }()
    override init(frame: CGRect) {
        super.init(frame: frame)
        initViewCoding()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    func setUpCell(movie:MovieCellViewModel){
        self.posterImage.image = movie.image
        titleLabel.attributedText = movie.title
        
    }

    

}
extension MovieCollectionViewCell:ViewCoding{
    func buildViewHierarchy() {
        self.contentView.addSubview(posterImage)
        self.addSubview(titleLabel)
    }

    func setUpConstraints() {
        titleLabel.anchor(top: self.contentView.topAnchor, leading: self.contentView.leadingAnchor, bottom: nil, trailing: self.contentView.trailingAnchor)
        posterImage.anchor(top: self.contentView.topAnchor, leading: self.contentView.leadingAnchor, bottom: self.contentView.bottomAnchor, trailing: self.contentView.trailingAnchor)
       
    }


    


}
