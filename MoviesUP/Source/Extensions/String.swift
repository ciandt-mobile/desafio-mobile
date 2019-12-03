//
//  String.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 02/12/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import Foundation

extension String {

    func localized() -> String {
        let localizedString = NSLocalizedString(self, comment: "")
        return localizedString
    }
}
