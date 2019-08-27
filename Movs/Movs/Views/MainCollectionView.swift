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
        //user layout as parameter
        let layout = MainCollectionLayout()
        layout.scrollDirection = .horizontal
        self.customLayout = layout
        super.init(frame: CGRect.zero
            , collectionViewLayout:layout )
        self.register(MainMovieCollectionViewCell.self, forCellWithReuseIdentifier:MainMovieCollectionViewCell.reuseIdentifier )
        self.backgroundColor = .clear
        
       
    }
    override func layoutMarginsDidChange() {
        resizeCells(size: self.bounds.size)
    }
    func scrollToStart(){
        self.scrollToItem(at: IndexPath.init(item: 0, section: 0), at: .centeredHorizontally, animated: true)
    }
    func resizeCells(size:CGSize){
        let ratio = size.height/size.width
        if(ratio>=1){
            customLayout.itemSize = CGSize(width: size.width * 0.8, height: size.height * 0.7)
        }else{
            customLayout.itemSize = CGSize(width: size.width * 0.3, height: size.height * 0.8)
        }
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}





