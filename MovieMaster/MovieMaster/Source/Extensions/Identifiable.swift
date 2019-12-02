//
//  Identifiable.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 30/11/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation
import UIKit

// MARK: - Identifiable protocol

protocol Identifiable: class {
    
}

extension Identifiable where Self: UIViewController {
    
    static var storyboardIdentifier: String {
        return String(describing: self)
    }
    
    static var segueIdentifier: String {
        let identifier = String(describing: self)
        let vcString = "ViewController"
        let endIndex = identifier.index(identifier.endIndex, offsetBy: -vcString.count)
        return String(identifier[identifier.startIndex..<endIndex])
    }
}

extension Identifiable where Self: UITableViewCell {
    static var reuseIdentifier: String {
        return String(describing: self)
    }
}

extension Identifiable where Self: UICollectionViewCell {
    static var reuseIdentifier: String {
        return String(describing: self)
    }
}
