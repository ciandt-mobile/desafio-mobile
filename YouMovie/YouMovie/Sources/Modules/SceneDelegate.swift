//
//  SceneDelegate.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    
    // MARK: - Internal Properties

    var window: UIWindow?
    
    // MARK: - Internal Methods

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        self.setupWindow(windowScene)
    }
    
    // MARK: - Private Methods

    private func setupWindow(_ windowScene: UIWindowScene) {

    }
}

