//
//  DateHelper.swift
//  MovieApp
//
//  Created by Michele de Olivio Corrêa on 20/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import Foundation

class DateHelper {
    public static let shared = DateHelper()
    private init() {}
    
    private func currentDateFormatter() -> DateFormatter {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-dd-mm"
        return dateFormatter
    }
    
    func convertDateFormatter(from date: String) -> String? {
        let dateFormatter = currentDateFormatter()
        
        guard let date = dateFormatter.date(from: date) else {
            return nil
        }
        
        dateFormatter.dateFormat = "MM/dd/yyyy"
        
        return dateFormatter.string(from: date)
    }
    
    func year(from date: String) -> Int? {
        let dateFormatter = currentDateFormatter()

        guard let date = dateFormatter.date(from: date) else {
            return nil
        }
        
        return Calendar.current.component(.year, from: date)
    }
    
}
