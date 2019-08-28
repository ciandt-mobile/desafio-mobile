//
//  AppDelegate.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        let window = UIWindow(frame: UIScreen.main.bounds)
        window.makeKeyAndVisible()
        let apiAccess = APIClient()
        let view = UINavigationController(rootViewController: PopularController(apiAccess: apiAccess))
        self.window = window
        window.rootViewController = view
        
        let navigationAppearence = UINavigationBar.appearance()
        navigationAppearence.barTintColor = UsedColors.gray.color
        navigationAppearence.prefersLargeTitles = true
        
        return true
    }
}

