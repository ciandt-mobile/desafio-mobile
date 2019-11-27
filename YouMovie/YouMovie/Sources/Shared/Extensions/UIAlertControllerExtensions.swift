//
//  UIAlertController.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

extension UIAlertController {

    /// Method used to create alert controllers
    ///
    /// - Parameters:
    ///   - title: Title of the alert
    ///   - message: Message of the alert
    ///   - style: Style of the alert
    ///   - actions: Actions to be added to alert
    /// - Returns: Returns UIAlertController
    static func alert(withTitle title: String,
                      message: String,
                      style: UIAlertController.Style,
                      actions: [UIAlertAction]) -> UIAlertController {
        let alert = UIAlertController(title: title, message: message, preferredStyle: style)

        actions.forEach { action in
            alert.addAction(action)
        }

        return alert
    }
}
