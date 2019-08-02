//
//  Cache.swift
//  Movies
//
//  Created by Piero Mattos on 29/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import Foundation

struct Cache {
    private static var store = NSCache<NSString, AnyObject>()

    static func getValue(forKey key: String) -> Any? {
        return store.object(forKey: NSString(string: key)) as Any
    }

    static func setValue(_ value: Any, forKey key: String) {
        store.setObject(value as AnyObject, forKey: NSString(string: key))
    }
}
