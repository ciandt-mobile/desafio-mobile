//
//  utils.swift
//  desafiomobile
//
//  Created by Erasmo Oliveira on 21/07/19.
//  Copyright © 2019 Erasmo Oliveira. All rights reserved.
//

import UIKit

public protocol ReusableTableViewCell {
    
}

extension UITableViewCell: ReusableTableViewCell {
    
}

public extension ReusableTableViewCell where Self: UITableViewCell {
    
    static var cellIdentifier: String {
        return "\(Self.self)"
    }
    
    public static func registerClass(for tableView: UITableView) {
        tableView.register(self.classForCoder(), forCellReuseIdentifier: cellIdentifier)
    }
    
    public static func registerNib(for tableView: UITableView) {
        let nib = UINib(nibName: cellIdentifier, bundle: nil)
        tableView.register(nib, forCellReuseIdentifier: cellIdentifier)
    }
    
    public static func registerNibWith(name nibName: String, for tableView: UITableView) {
        let nib = UINib(nibName: nibName, bundle: nil)
        tableView.register(nib, forCellReuseIdentifier: cellIdentifier)
    }
    
    public static func dequeueCell(from tableView: UITableView?, for indexPath: IndexPath? = nil) -> Self {
        if let indexPath = indexPath, let cell = tableView?.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? Self {
            return cell
        } else if let cell = tableView?.dequeueReusableCell(withIdentifier: cellIdentifier) as? Self {
            return cell
        } else {
            return Self()
        }
    }
}


public protocol ReusableCollectionViewCell {
    
}

extension UICollectionViewCell: ReusableCollectionViewCell {
    
}

public extension ReusableCollectionViewCell where Self: UICollectionViewCell {
    
    static var cellIdentifier: String {
        return "\(Self.self)"
    }
    
    public static func registerNib(for collection: UICollectionView) {
        let nib = UINib(nibName: cellIdentifier, bundle: nil)
        collection.register(nib, forCellWithReuseIdentifier: cellIdentifier)
    }
    
    public static func dequeueCell(from collection: UICollectionView?, for indexPath: IndexPath) -> Self {
        let cell = collection?.dequeueReusableCell(withReuseIdentifier: cellIdentifier, for: indexPath) as? Self
        return cell ?? Self()
    }
}

