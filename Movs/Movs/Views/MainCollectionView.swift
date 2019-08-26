//
//  MainCollectionView.swift
//  Movs
//
//  Created by Eduardo Pereira on 28/07/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class MainCollectionView: UICollectionView {
    private let customLayout:MainCollectionLayout
    init() {
        let layout = MainCollectionLayout()
        layout.itemSize = CGSize(width: MainMovieCollectionViewCell.cellWidth, height: MainMovieCollectionViewCell.cellHeight)
        layout.scrollDirection = .horizontal
        self.customLayout = layout
        super.init(frame: CGRect.zero
            , collectionViewLayout:layout )
        self.register(MainMovieCollectionViewCell.self, forCellWithReuseIdentifier:MainMovieCollectionViewCell.reuseIdentifier )
        self.backgroundColor = .clear
        
       
    }

    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}





