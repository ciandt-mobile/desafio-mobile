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
    let collection:MainCollectionView = {
        let collection = MainCollectionView(registerCell: { (collection) in
            collection.register(MovieCollectionViewCell.self, forCellWithReuseIdentifier:MovieCollectionViewCell.reuseIdentifier )
        },layout: MainCollectionLayout())
        return collection
    }()
    var segment = UISegmentedControl()
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
        viewModel.configureNavBar(navController: self.navigationController)
        collection.delegate = viewModel
        collection.dataSource = viewModel
        viewModel.didSelect = { [weak self](cellViewModel) in
            guard let self = self else{
                return
            }
            let detailController = DetailController(model: cellViewModel.getMovie(), dataAcess: self.dataAcess)
            self.navigationController?.pushViewController(detailController, animated: true)
        }
        segment = SimpleSegment(items: viewModel.segmentItens)
        segment.addTarget(self, action: #selector(self.indexChanged(_:)), for: .valueChanged)
        self.navigationItem.titleView = segment
    }
    
    @objc func indexChanged(_ sender: UISegmentedControl) {
        switch sender.selectedSegmentIndex{
        case 0:
            viewModel.changeData(request: Request.upcoming)
            break
        case 1:
            viewModel.changeData(request: Request.popular)
            break
        default:
            break
        }
        collection.scrollToStart()
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
