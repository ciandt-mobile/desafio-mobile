//
//  AppDelegate.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/14/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import UIKit
import RxSwift
import RxFlow

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    let disposeBag = DisposeBag()
    var window: UIWindow?
    
    var coordinator = FlowCoordinator()
    var initialStep: Step {
        return
    }
    

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        window = UIWindow(frame: UIScreen.main.bounds)
        let homeViewController = MoviesViewController(nib: R.nib.moviesViewController)
        window!.rootViewController = UINavigationController(rootViewController: homeViewController)
        window!.makeKeyAndVisible()
        return true
    }

}

