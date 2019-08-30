//
//  HomeView.swift
//  Movs
//
//  Created by Eduardo Pereira on 28/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class HomeView:UIView{
    let backgroundImage = UIImageView()
    private let activityControl:UIActivityIndicatorView = {
        let activityView = UIActivityIndicatorView()
        activityView.hidesWhenStopped = true
        activityView.backgroundColor = .clear
        activityView.style = UIActivityIndicatorView.Style.whiteLarge
        return activityView
    }()
    let collection:MainCollectionView = {
        let collection = MainCollectionView(registerCell: { (collection) in
            collection.register(MovieCollectionViewCell.self, forCellWithReuseIdentifier:MovieCollectionViewCell.reuseIdentifier )
        },layout: MainCollectionLayout())
        return collection
    }()
    var segment = UISegmentedControl()
    override init(frame: CGRect) {
        super.init(frame: frame)
        initViewCoding()
    }
    func setUp(viewModel:HomeViewModel){
        collection.delegate = viewModel
        collection.dataSource = viewModel
        segment = SimpleSegment(items: viewModel.segmentItens)
        backgroundImage.image = viewModel.backgroundImage
        
    }
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
extension HomeView:ViewCoding{
    func buildViewHierarchy() {
        self.addSubview(backgroundImage)
        self.addSubview(collection)
        self.addSubview(activityControl)
    }
    
    func setUpConstraints() {
        backgroundImage.fillSuperview()
        collection.fillSuperview()
        activityControl.fillSuperview()
    }
    
}
extension HomeView:Refresh{
    func removeRefresher() {
        DispatchQueue.main.async {
            self.activityControl.stopAnimating()
        }
    }
    
    func addRefresher() {
        DispatchQueue.main.async {
            self.activityControl.isHidden = true
            self.activityControl.startAnimating()
        }
        
    }
    
    
}
