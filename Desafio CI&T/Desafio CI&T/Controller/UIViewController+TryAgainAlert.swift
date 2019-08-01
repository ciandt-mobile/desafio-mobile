//
//  UIViewController+TryAgainAlert.swift
//  Desafio CI&T
//
//  Created by Mateus Kamoei on 01/08/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import UIKit

extension UIViewController {
    
    func presentTryAgainAlert(with message: String, and callback: @escaping () -> ()) {
        let alert = UIAlertController(title: "Sorry", message: message, preferredStyle: .alert)
        let tryAgainAction = UIAlertAction(title: "Try again", style: .default, handler: { (action) in
            callback()
        })
        alert.addAction(tryAgainAction)
        self.present(alert, animated: true, completion: nil)
    }
    
}
