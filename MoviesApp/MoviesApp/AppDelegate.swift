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
import RxCocoa
import Swinject
import SwinjectAutoregistration

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    let disposeBag = DisposeBag()
    var window: UIWindow?
    
    var coordinator = FlowCoordinator()
    var initialStep: Step {
        return MoviesState.home
    }
    
    let container: Container = Container { container in
        container.register(UINavigationController.self) { (_, hideNavigationBar: Bool) in
            let viewController = UINavigationController()
            viewController.modalPresentationStyle = .fullScreen
            return viewController
        }
        container.autoregister(MoviesService.self, initializer: MoviesService.init).inObjectScope(.container)

        container.autoregister(MoviesFlow.self, initializer: MoviesFlow.init).inObjectScope(.container)
        
        container.register(Container.self) { _ in container}.inObjectScope(.container)
    }


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
//        window = UIWindow(frame: UIScreen.main.bounds)
        setupFlow()
        return true
    }
    
    private func setupFlow() {
        guard let window = self.window else {
            print("Deu ruim")
            return
        }
        let moviesFlow = container ~> MoviesFlow.self
        
        Flows.whenReady(flow1: moviesFlow) { (root) in
            window.rootViewController = root
            window.makeKeyAndVisible()
        }
        self.coordinator.coordinate(flow: moviesFlow, with: OneStepper(withSingleStep: initialStep))
    }
    
    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }

}

