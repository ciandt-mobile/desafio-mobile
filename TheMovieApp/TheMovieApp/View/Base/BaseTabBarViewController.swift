//
//  BaseTabBarViewController.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 24/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import UIKit

class BaseTabBarViewController: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupTabBar()
    }
    
    private func setupTabBar() {
        tabBar.tintColor = Colors.black
        tabBar.unselectedItemTintColor = Colors.black.withAlphaComponent(0.4)
        tabBar.barTintColor = Colors.yellow
    }
}
