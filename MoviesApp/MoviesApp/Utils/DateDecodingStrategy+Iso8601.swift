//
//  DateDecodingStrategy+Iso8601.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/18/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation

extension JSONDecoder.DateDecodingStrategy {
    static let customIso8601 = custom {
        let container = try $0.singleValueContainer()
        let string = try container.decode(String.self)
        if let date = Formatter.iso8601Date.date(from: string) {
            return date
        }
        throw DecodingError.dataCorruptedError(in: container, debugDescription: "Invalid Date Format: \(string)")
    }
}

extension Formatter {
    static let iso8601Date: DateFormatter = {
        let formatter = DateFormatter()
        formatter.calendar = Calendar(identifier: .iso8601)
        formatter.locale = Locale(identifier: "en_US_POSIX")
        formatter.timeZone = TimeZone(secondsFromGMT: 0)
        formatter.dateFormat = "yyy-MM-dd"
        return formatter
    }()
}
