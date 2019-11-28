//
//  AppDelegate.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    // MARK: - Internal Properties

    var window: UIWindow?
    
    // MARK: - Internal Methods

    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        self.setupWindow()
        return true
    }
    
    // MARK: - Private Methods

    private func setupWindow() {
        
        self.window = UIWindow(frame: UIScreen.main.bounds)
        guard let window = self.window else { return }

        let moviesView = MoviesWireframe().instantiateView()
        let navigationView = UINavigationController(rootViewController: moviesView)
        window.rootViewController = navigationView
        window.makeKeyAndVisible()
    }
}

