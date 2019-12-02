//
//  Formatter.swift
//  MovieMaster
//
//  Created by Andre & Bianca on 01/12/19.
//  Copyright © 2019 Andre. All rights reserved.
//

import Foundation

class Formatter {
    // MARK: - Init
    init() {}
    
    static func formatBrazilianDate(dateString: String) -> String {
        let inputDateFormatter = DateFormatter()
        inputDateFormatter.locale = Locale.init(identifier: "pt_BR")
        inputDateFormatter.dateFormat = "yyyy-MM-dd"
        if let date = inputDateFormatter.date(from: dateString) {
            let outputDateFormatter = DateFormatter()
            outputDateFormatter.locale = Locale.init(identifier: "pt_BR")

            outputDateFormatter.dateFormat = "dd/MM/yyyy"
            return outputDateFormatter.string(from: date)
        }
        return "-"
    }

    static func getYear(dateString: String) -> String {
        let inputDateFormatter = DateFormatter()
        inputDateFormatter.locale = Locale.init(identifier: "pt_BR")
        inputDateFormatter.dateFormat = "yyyy-MM-dd"
        if let date = inputDateFormatter.date(from: dateString) {
            let outputDateFormatter = DateFormatter()
            outputDateFormatter.locale = Locale.init(identifier: "pt_BR")

            outputDateFormatter.dateFormat = "yyyy"
            return outputDateFormatter.string(from: date)
        }
        return "-"
    }
    
}
