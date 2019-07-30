//
//  BaseNavigationViewController.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 24/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import UIKit

class BaseNavigationViewController: UINavigationController {

    override func viewDidLoad() {
        super.viewDidLoad()

        self.navigationBar.barTintColor = Colors.yellow
        self.navigationBar.tintColor = Colors.marineBlue
        self.navigationBar.isTranslucent = true
    }
}
