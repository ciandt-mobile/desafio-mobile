//
//  CrewMember.swift
//  Movies
//
//  Created by Piero Mattos on 28/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import Foundation

struct CrewMember: Decodable {

    // MARK: - Properties

    let id: Int
    let name: String
    let department: String
    let job: String
    let profilePictureURL: URL?

    // MARK: - Methods

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        id = try container.decode(Int.self, forKey: .id)
        name = try container.decode(String.self, forKey: .name)
        department = try container.decode(String.self, forKey: .department)
        job = try container.decode(String.self, forKey: .job)

        if let profilePath = try? container.decode(String.self, forKey: .profilePicturePath) {
            profilePictureURL = URL(string: "https://image.tmdb.org/t/p/w185\(profilePath)")!
        } else {
            profilePictureURL = nil
        }
    }

    // MARK: - Enums

    enum CodingKeys: String, CodingKey {
        case id, name, department, job
        case profilePicturePath = "profilePath"
    }
}

// MARK: - PersonRepresentable

extension CrewMember: PersonRepresentable {
    var role: String { return job }
}
