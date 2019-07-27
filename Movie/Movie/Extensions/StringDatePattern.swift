//
//  DateFormatter+Pattern.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import Foundation

extension String {
    
    func convertDateFormater() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let newDate = dateFormatter.date(from: self)
        dateFormatter.dateFormat = "dd/MM/yyyy"
        return dateFormatter.string(from: newDate!)
    }
    
    func todayDate() -> String {
        let date = Date()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        return dateFormatter.string(from: date)
    }
    
    func getYearDate() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let calendar = Calendar.current
        let year = calendar.component(.year, from: dateFormatter.date(from: self)!)
        return year.description
    }
    
}
