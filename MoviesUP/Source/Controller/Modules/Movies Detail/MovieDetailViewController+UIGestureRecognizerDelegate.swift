//
//  MovieDetailViewController+UIGestureRecognizerDelegate.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 27/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

//MARK: UIGestureRecognizerDelegate
extension MovieDetailViewController: UIGestureRecognizerDelegate {
    
    func setInteractivePopGesture() {
        navigationController?.interactivePopGestureRecognizer?.delegate = self
    }
    
    func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        if let navigationController = navigationController, navigationController.viewControllers.count > 1 {
            return true
        }
        return false
    }
}
