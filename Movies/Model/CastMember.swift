//
//  CastMember.swift
//  Movies
//
//  Created by Piero Mattos on 28/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import Foundation

struct CastMember: Decodable {

    // MARK: - Properties

    let id: Int
    let name: String
    let characterName: String
    let order: Int
    let profilePictureURL: URL?

    // MARK: - Methods

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        id = try container.decode(Int.self, forKey: .id)
        name = try container.decode(String.self, forKey: .name)
        characterName = try container.decode(String.self, forKey: .characterName)
        order = try container.decode(Int.self, forKey: .order)

        if let profilePath = try? container.decode(String.self, forKey: .profilePicturePath) {
            profilePictureURL = URL(string: "https://image.tmdb.org/t/p/w185\(profilePath)")!
        } else {
            profilePictureURL = nil
        }
    }

    // MARK: - Enums

    enum CodingKeys: String, CodingKey {
        case id = "castId"
        case name
        case characterName = "character"
        case order
        case profilePicturePath = "profilePath"
    }
}

// MARK: - PersonRepresentable

extension CastMember: PersonRepresentable {
    var role: String { return characterName }
}
