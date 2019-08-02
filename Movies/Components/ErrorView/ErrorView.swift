//
//  ErrorView.swift
//  Movies
//
//  Created by Piero Mattos on 01/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

class ErrorView: UIStackView {

    // MARK: - Properties

    @IBOutlet weak var errorDescriptionLabel: UILabel!

    // MARK: - Methods

    static func loadFromNib() -> ErrorView {
        guard
            let nib = Bundle.main.loadNibNamed("ErrorView", owner: nil, options: nil),
            let errorView = nib[0] as? ErrorView
            else { fatalError("Could not load ErrorView from xib file.") }
        return errorView
    }
}
