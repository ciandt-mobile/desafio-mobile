//
//  DoubleExtensions.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation

extension Double {

    /// Formats value to rounded string
    var roundedString: String? {
        let formatter = NumberFormatter()
        let number = NSNumber(value: self)
        formatter.minimumFractionDigits = 0
        formatter.maximumFractionDigits = 16 // Maximum Digits in Double after dot
        return formatter.string(from: number)
    }

    /// Formats value to en-US Currency using $ symbol
    var formattedCurrency: String? {
        guard self != 0 else { return "-" }
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.locale = Locale(identifier: "en-US")
        return formatter.string(from: NSNumber(value: self))
    }
}
