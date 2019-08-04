//
//  HasCustomView.swift
//  iMovie
//
//  Created by João Tocilo on 31/07/19.
//  Copyright © 2019 Fulltime. All rights reserved.
//

import Foundation
import UIKit

public protocol HasCustomView {
    associatedtype CustomView: UIView
}

extension HasCustomView where Self: UIViewController {
    public var customView: CustomView {
        guard let customView = view as? CustomView else {
            fatalError("Expected view to be of type \(CustomView.self) but got \(type(of: view)) instead")
        }
        return customView
    }
}
