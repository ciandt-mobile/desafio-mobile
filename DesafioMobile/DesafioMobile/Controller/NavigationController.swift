//
//  NavigationController.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import UIKit


class NavigationController: UINavigationController{
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupTabBar()
    }

    

    //MARK: - Tab Bar Configuration
    func setupTabBar(){
        
        let apiAccess = APIClient()        
        let mainController = PopularController(apiAccess: apiAccess)
        let upComing = PopularController(apiAccess: apiAccess)
    
        viewControllers = [mainController,upComing]
    }
    
}
