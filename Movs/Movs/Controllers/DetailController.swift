//
//  DetailController.swift
//  Movs
//
//  Created by Eduardo Pereira on 27/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class DetailController: UIViewController {
    var viewModel:DetailViewModel!
    let detailView = DetailView()
    let dataAcess:DataAcess
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        initViewCoding()
        viewModel.configureNavBar(navController: self.navigationController)
    }
    init(model:Movie,dataAcess:DataAcess){
        self.dataAcess  = dataAcess
        super.init(nibName: nil, bundle: nil)
        viewModel = DetailViewModel(model: model, dataAcess: dataAcess,uiHandler: {
            DispatchQueue.main.async {
                [weak self] in
                guard let self = self else{
                    return
                }
                self.detailView.setup(viewModel: self.viewModel)
                self.detailView.reloadCollection()
            }
        } )
        detailView.youtbeButton.addTarget(self, action: #selector(self.requestYoutube), for: .touchUpInside)
        detailView.artistCollection.delegate = viewModel
        detailView.artistCollection.dataSource = viewModel
        
    }
    @objc
    func requestYoutube(){
        viewModel.requestYoutube()
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}
extension DetailController:ViewCoding{
    func buildViewHierarchy() {
        self.view.addSubview(detailView)
    }
    
    func setUpConstraints() {
        detailView.fillSuperview()
    }
    
    
}
