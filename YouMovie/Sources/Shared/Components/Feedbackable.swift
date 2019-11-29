//
//  Feedbackable.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

protocol Feedbackable {
    func showAlert(withTitle title: String,
                   message: String, actions: [UIAlertAction],
                   completion: (() -> Void)?)
}

extension Feedbackable {

    /// Present Alert at TopViewController
    ///
    /// - Parameters:
    ///   - title: Title to be displayed
    ///   - message: Message to be displayed
    ///   - actions: Actions to be triggered at alert
    ///   - completion: Returns block when alert is presented
    func showAlert(withTitle title: String,
                   message: String,
                   actions: [UIAlertAction],
                   completion: (() -> Void)? = nil) {
        guard let rootViewController = UIApplication.shared.windows.first?.rootViewController else { return }
        let topViewController = rootViewController.presentedViewController ?? rootViewController
        let alert = UIAlertController.alert(withTitle: title, message: message, style: .alert, actions: actions)
        alert.view.tintColor = .black

        topViewController.present(alert, animated: true, completion: completion)
    }
}
