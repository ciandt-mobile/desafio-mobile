//
//  MovieDetailView.swift
//  iMovie
//
//  Created by João Tocilo on 02/08/19.
//  Copyright © 2019 Fulltime. All rights reserved.
//

import Foundation
import UIKit
import SnapKit

class MovieDetailView: UIView {
    
    let cellId = "cellIdCast"
    var collectionView : UICollectionView!
    
    let scrollView : UIScrollView = {
        let scrollView = UIScrollView()
        return scrollView
    }()
    
    private let containerView : UIView = {
        let view = UIView()
        return view
    }()
    
    let posterImage : UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = UIView.ContentMode.scaleAspectFill
        return imageView
    }()
    
    let labelName : UILabel = {
        let lbl = UILabel()
        lbl.textColor = .white
        lbl.font = .boldSystemFont(ofSize: 20)
        lbl.textAlignment = .center
        lbl.numberOfLines = 3
        return lbl
    }()
    
    let labelInfo : UILabel = {
        let lbl = UILabel()
        lbl.textColor = UIColor(red: 95 / 255, green: 95 / 255, blue: 95 / 255, alpha: 1)
        lbl.font = .systemFont(ofSize: 12)
        lbl.textAlignment = .center
        lbl.numberOfLines = 3
        return lbl
    }()
    
    let labelOverview : UILabel = {
        let lbl = UILabel()
        lbl.textColor = UIColor(red: 95 / 255, green: 95 / 255, blue: 95 / 255, alpha: 1)
        lbl.font = .systemFont(ofSize: 14)
        lbl.textAlignment = .center
        lbl.numberOfLines = 0
        return lbl
    }()
    
    func render() {
        
        backgroundColor = UIColor(red: 20 / 255, green: 20 / 255, blue: 20 / 255, alpha: 1)
        
        let layoutCollectionView: UICollectionViewFlowLayout = UICollectionViewFlowLayout()
        layoutCollectionView.sectionInset = UIEdgeInsets(top: 10, left: 10, bottom: 10, right: 10)
        layoutCollectionView.itemSize = CGSize(width: 100, height: 160)
        layoutCollectionView.scrollDirection = .horizontal
        
        collectionView = UICollectionView(frame: CGRect(), collectionViewLayout: layoutCollectionView)
        collectionView.backgroundColor = UIColor(red: 20 / 255, green: 20 / 255, blue: 20 / 255, alpha: 1)
        collectionView.register(CastListCell.self, forCellWithReuseIdentifier: cellId)
        
        addSubview(scrollView)
        scrollView.snp.makeConstraints { (make) in
            make.leading.top.trailing.bottom.equalToSuperview()
        }
        
        scrollView.addSubview(containerView)
        containerView.snp.makeConstraints { (make) in
            make.height.width.equalToSuperview()
        }
        
        containerView.addSubview(posterImage)
        posterImage.snp.makeConstraints { (make) in
            make.trailing.top.leading.equalToSuperview()
            make.height.equalTo(200)
        }

        containerView.addSubview(labelName)
        labelName.snp.makeConstraints { (make) in
            make.leading.equalToSuperview().offset(10)
            make.trailing.equalToSuperview().inset(10)
            make.top.equalTo(posterImage.snp.bottom).offset(15)
        }
        
        containerView.addSubview(labelInfo)
        labelInfo.snp.makeConstraints { (make) in
            make.leading.equalToSuperview().offset(10)
            make.trailing.equalToSuperview().inset(10)
            make.top.equalTo(labelName.snp.bottom).offset(15)
        }
        
        containerView.addSubview(collectionView)
        collectionView.snp.makeConstraints { (make) in
            make.leading.trailing.equalToSuperview()
            make.top.equalTo(labelInfo.snp.bottom).offset(10)
            make.height.equalTo(200)
        }
        
        containerView.addSubview(labelOverview)
        labelOverview.snp.makeConstraints { (make) in
            make.leading.equalToSuperview().offset(10)
            make.trailing.equalToSuperview().inset(10)
            make.top.equalTo(collectionView.snp.bottom).offset(10)
        }
    }
    
}
