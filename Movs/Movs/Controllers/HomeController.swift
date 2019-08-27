//
//  HomeController.swift
//  Movs
//
//  Created by Eduardo Pereira on 26/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class HomeController:UIViewController{
    let dataAcess:DataAcess
    let backgroundImage = UIImageView()
    let collection = MainCollectionView()
    let viewModel:HomeViewModel
    init(dataAcess:DataAcess) {
        viewModel = HomeViewModel(dataAcess: dataAcess)
        self.dataAcess = dataAcess
        super.init(nibName: nil, bundle: nil)
        
        viewModel.uiHandler = {
            DispatchQueue.main.async {[weak self] in
                 self?.collection.reloadData()
            }
        }
    }
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        initViewCoding()
        collection.delegate = viewModel
        collection.dataSource = viewModel
    }
    
}
extension HomeController:ViewCoding{
    func buildViewHierarchy() {
        self.view.addSubview(backgroundImage)
        self.view.addSubview(collection)
    }
    
    func setUpConstraints() {
        backgroundImage.fillSuperview()
        collection.fillSuperview()
    }
    
}
