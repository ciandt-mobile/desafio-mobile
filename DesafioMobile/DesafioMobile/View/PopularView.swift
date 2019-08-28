//
//  MovieGridView.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import UIKit
import SnapKit

//MARK: - The view basic configuration and visual elements
class PopularView: UIView{
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    lazy var gridView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        let view = UICollectionView(frame: .zero, collectionViewLayout: layout)
        return view
    }()
    
    lazy var loadRoll: UIActivityIndicatorView = {
        let view = UIActivityIndicatorView(frame: .zero)
        view.color = UsedColors.white.color
        return view
    }()
    
    lazy var loadingLabel: UILabel = {
        let view = UILabel()
        view.text = "Loading movies!"
        view.textColor = UsedColors.white.color
        view.font = UIFont.systemFont(ofSize: 35)
        view.numberOfLines = 0
        view.textAlignment = .center
        view.isHidden = false
        return view
    }()
    
    lazy var customSC: UISegmentedControl = {
        let items = ["Popular","Upcoming"]
        let control = UISegmentedControl(items: items)
        control.tintColor = UsedColors.black.color
        control.selectedSegmentIndex = 0
        return control
    }()
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}


extension PopularView{
    func resetGrid(){
        let index = IndexPath(row: 0, section: 0)
        gridView.scrollToItem(at: index, at: UICollectionView.ScrollPosition.top, animated: false)
        gridView.isHidden = true
        loadRoll.isHidden = false
        loadingLabel.isHidden = false
        gridView.reloadData()
        loadRoll.startAnimating()
    }
    
    func loadGrid(){
        gridView.reloadData()
        loadRoll.stopAnimating()
        loadRoll.isHidden = true
        loadingLabel.isHidden = true
        gridView.isHidden = false
    }
}



//MARK: - Extension to define the cell constraints
extension PopularView: CodeView{
    func buildViewHierarchy() {
        addSubview(gridView)
        addSubview(loadingLabel)
        addSubview(loadRoll)
        bringSubviewToFront(loadRoll)
    }
    
    func setupConstrains() {
        gridView.snp.makeConstraints { (make) in
            make.left.right.top.bottom.equalToSuperview()
        }
        
        loadingLabel.snp.makeConstraints { (make) in
            make.left.right.equalToSuperview()
            make.bottom.equalToSuperview().multipliedBy(0.5)
            make.height.equalToSuperview().multipliedBy(0.2)
        }
        
        loadRoll.snp.makeConstraints { (make) in
            make.right.left.top.bottom.equalToSuperview()
        }
        
    }
    
    func setupAdditionalConfiguration() {
        gridView.backgroundColor = UsedColors.black.color
        gridView.register(PopularViewCell.self, forCellWithReuseIdentifier: PopularViewCell.reuseIdentifier)
    }
}


