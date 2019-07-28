//
//  BaseViewController.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation
import UIKit

class BaseViewController: UIViewController {
    
    //MARK: - Private Var's
    private var pendingAction: (() -> Void)?
    
    //MARK: - Public Var's
    public static var loadingAlert: UIAlertController = {
        let loadingAlert = UIAlertController(title: nil, message: "Loading...", preferredStyle: .alert)
        
        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.style = UIActivityIndicatorView.Style.gray
        loadingIndicator.startAnimating()
        
        loadingAlert.view.addSubview(loadingIndicator)
        
        return loadingAlert
    }()
    
    //MARK: - View Lifecycle
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if pendingAction != nil {
            showLoadingAlert()
        }
    }

    //MARK: - Internal Method's
    internal func showErrorAlert(title: String, message: String, handler: ((UIAlertAction) -> Void)? = nil) {
        guard presentedViewController == nil else { return }

        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: "OK", style: .default) { alertAction in
            handler?(alertAction)
        }
        alert.addAction(okAction)
        self.present(alert, animated: true, completion: nil)
    }
    
    internal func showLoadingAlert(completion: (() -> Void)? = nil) {
        guard isViewLoaded else {
            pendingAction = completion
            completion?()
            return
        }
        present(BaseViewController.loadingAlert, animated: true, completion: completion)
    }
    
    internal func dismissLoadingAlert(completion: (() -> Void)? = nil) {
        pendingAction = nil
        BaseViewController.loadingAlert.dismiss(animated: true, completion: completion)
    }
}
