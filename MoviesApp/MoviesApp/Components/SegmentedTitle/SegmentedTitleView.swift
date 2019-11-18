//
//  SegmentedTitleView.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/14/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import UIKit

class SegmentedTitleView: UIView {

    var view: UIView!

    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupView()
    }
    
    private func setupView() {
        backgroundColor = .clear
        view = R.nib.segmentedTitleView(owner: self, options: nil)
        self.addSubview(view)
        view.frame = self.bounds
        view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        view.backgroundColor = .red
        
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        view.frame = self.bounds
    }

}
