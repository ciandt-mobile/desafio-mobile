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
    let homeView:HomeView
    lazy var viewModel:HomeViewModel = {
        return HomeViewModel(dataAcess: self.dataAcess, uiHandler: {
            DispatchQueue.main.async {[weak self] in
                self?.homeView.collection.reloadData()
            }
        },refreshHandler: homeView)
    }()
    init(dataAcess:DataAcess) {
        self.homeView = HomeView()
        self.dataAcess = dataAcess
        super.init(nibName: nil, bundle: nil)
        homeView.setUp(viewModel: viewModel)
    }
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        viewModel.configureNavBar(navController: self.navigationController)
        updateViewConstraints()
       
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        initViewCoding()
        viewModel.didSelect = { [weak self](cellViewModel) in
            guard let self = self else{
                return
            }
            let detailController = DetailController(model: cellViewModel.getMovie(), dataAcess: self.dataAcess)
            self.navigationController?.pushViewController(detailController, animated: true)
        }
        homeView.segment.addTarget(self, action: #selector(self.indexChanged(_:)), for: .valueChanged)
         self.navigationItem.titleView = homeView.segment
        
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
        self.homeView.collection.scrollToStart()
    }
 
 
}
extension HomeController:ViewCoding{
    func buildViewHierarchy() {
        self.view.addSubview(homeView)
       
    }
    
    func setUpConstraints() {
         homeView.fillSuperview()
    }
    
    
}


