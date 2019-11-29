//
//  Instantiable.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import UIKit

// MARK: - Instantiable Protocol

protocol Instantiable {
    static func instantiate() -> Self
}

extension Instantiable where Self: UIView {

    /// - Returns: The UIView to be instantiated from UINib.
    static func instantiate() -> Self {
        let nib = UINib(nibName: self.reuseIdentifier, bundle: nil)
        let view = nib.instantiate(withOwner: nil, options: nil).first as! Self
        return view
    }
}
