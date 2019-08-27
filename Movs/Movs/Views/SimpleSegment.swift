//
//  SimpleSegment.swift
//  Movs
//
//  Created by Eduardo Pereira on 27/08/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

class SimpleSegment: UISegmentedControl {
    override init(items: [Any]?) {
        super.init(items: items)
        self.selectedSegmentIndex = 1
        self.layer.cornerRadius = 5.0
        self.backgroundColor = .clear
        self.tintColor = Color.oldPaper
    }
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
