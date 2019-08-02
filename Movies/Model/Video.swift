//
//  Video.swift
//  Movies
//
//  Created by Piero Mattos on 28/07/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import Foundation

struct Video: Decodable {

    // MARK: - Properties

    let id: String
    let key: String
    let name: String
    let site: String
    let type: String

    var isYouTube: Bool { return site == "YouTube" }
    var isTrailer: Bool { return type == "Trailer" }

    var youTubeURL: URL? {
        if isYouTube {
            return URL(string: "https://www.youtube.com/embed/\(key)")
        } else {
            return nil
        }
    }
}
