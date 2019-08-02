//
//  AppDelegate.swift
//  Movies
//
//  Created by Piero Mattos on 26/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit
import HTTPeasy

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {

        let customConfig = URLSessionConfiguration.default
        customConfig.requestCachePolicy = .returnCacheDataElseLoad
        customConfig.timeoutIntervalForRequest = 60.0
        APIRequester.shared = APIRequester(sessionConfiguration: customConfig)

        return true
    }
}
