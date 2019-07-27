//
//  SegueID.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation
import UIKit

enum SegueID: String {
    case toMovieDetails = "moviesListToMovieDetailSegue"
}

extension UIViewController {
    func performSegue(_ segueID: SegueID, sender: Any? = self) {
        self.performSegue(withIdentifier: segueID.rawValue, sender: sender)
    }
}
