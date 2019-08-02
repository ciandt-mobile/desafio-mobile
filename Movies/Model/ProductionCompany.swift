//
//  ProductionCompany.swift
//  Movies
//
//  Created by Piero Mattos on 28/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import Foundation

struct ProductionCompany: Decodable {

    // MARK: - Properties

    let logoURL: URL
    let name: String

    // MARK: - Methods

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        let logoPath = try container.decode(String.self, forKey: .logoPath)
        logoURL = URL(string: logoPath)!

        name = try container.decode(String.self, forKey: .name)
    }

    // MARK: - Enums

    enum CodingKeys: String, CodingKey {
        case logoPath
        case name
    }
}
