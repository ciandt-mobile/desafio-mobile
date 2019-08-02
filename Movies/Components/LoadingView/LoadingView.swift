//
//  LoadingView.swift
//  Movies
//
//  Created by Piero Mattos on 01/08/19.
//  Copyright © 2019 Piero Mattos. All rights reserved.
//

import UIKit

class LoadingView: UIStackView {

    // MARK: - Properties

    @IBOutlet weak var loadingMessage: UILabel!

    // MARK: - Methods

    static func loadFromNib() -> LoadingView {
        guard
            let nib = Bundle.main.loadNibNamed("LoadingView", owner: nil, options: nil),
            let loadingView = nib[0] as? LoadingView
            else { fatalError("Could not load LoadingView from xib file.") }
        return loadingView
    }
}
