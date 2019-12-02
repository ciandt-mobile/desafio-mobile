//
//  Notification+Extension.swift
//  movies
//
//  Created by Arthur Silva on 12/1/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import Foundation

extension Notification.Name {
    static let didStartedDownloading = Notification.Name("didStartedDownloading")
    static let didReceivedAnError = Notification.Name("didReceivedAnError")
}
