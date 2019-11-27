//
//  DateFormatter.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 25/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import Foundation

extension String {

    //MARK: Extensions Date
    func getDate() -> Date? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-mm-dd"
        return dateFormatter.date(from: self)
    }
}

extension Date {
    
    var components: DateComponents {
        let calendar = Calendar.current
        let components = calendar.dateComponents([.day, .month, .year], from: self)
        return components
    }
    
    func getYearString() -> String? {
        return components.year?.description
    }

    func getFormattedDate() -> String {
        let dateFormatterGet = DateFormatter()
        dateFormatterGet.dateFormat = "yyyy-mm-dd"
        let dateFormatterPrint = DateFormatter()
        dateFormatterPrint.dateFormat = "dd/mm/yy"
        return dateFormatterPrint.string(from: self)
    }
}
