//
//  EmptyView.swift
//  storeappios
//
//  Created by Wilson Kim on 25/06/19.
//  Copyright © 2019 RevMob. All rights reserved.
//

import UIKit

@IBDesignable
class EmptyView:UIView {
    
    @IBOutlet weak var contentView: UIView!
    @IBOutlet weak var mainImageView: UIImageView!
    @IBOutlet weak var errorMessageLabel: UILabel!
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        commonInit()
    }
    
    private func commonInit() {
        loadViewFromNib()
        setup()
    }
    
    private func setup() {
        contentView.layer.cornerRadius = 16
    }
    
    public func setEmptyMessage(_ message:String?) {
        errorMessageLabel.text = message
    }
    
    public func setImage(_ image:UIImage?) {
        mainImageView.image = image
    }
}

extension EmptyView: UpdateStateProtocol {
    func shouldUpdateView(_ state: ViewStateEnum) {
        switch state {
        case let .empty(message, image: image):
            if let image = image { setImage(image) }
            setEmptyMessage(message)
            isHidden = false
            break
        default:
            isHidden = true
        }
    }
}
