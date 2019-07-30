//
//  ErrorView.swift
//  ECobranca
//
//  Created by Wilson Kim on 18/01/19.
//  Copyright © 2019 Euler Carvalho. All rights reserved.
//

import UIKit

public class ErrorView: UIView {
    
    @IBOutlet var contentView: UIView!
    @IBOutlet weak var containerView: UIView!
    @IBOutlet weak var errorImageView: UIImageView!
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var message: UILabel!
    @IBOutlet weak var retryCallButton: AppButton!
    
    var errorRetryBlock: (() -> Void) = { }
    
    override public var backgroundColor: UIColor? {
        didSet {
            self.contentView.backgroundColor = backgroundColor
        }
    }
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        setup()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setup()
    }
    
    public func setup() {
        loadViewFromNib()
        setupMessageLabel()
        setupTitleLabel()
        backgroundColor = UIColor.clear
        setupButton()
        setupContainerView()
    }
    
    private func setupButton() {
        retryCallButton.setImage(UIImage(named: "icon_refresh")?.withRenderingMode(.alwaysTemplate), for: .normal)
    }
    
    public func setupMessageLabel() {
        message.textColor = Colors.darkGray
    }
    
    public func setupTitleLabel() {
        title.textColor = Colors.darkGray
    }
    
    private func setupContainerView() {
        containerView.layer.cornerRadius = 16
    }
    
    public func setErrorMessage(_ text:String?) {
        message.text = text
    }
    
    public func setErrorTitle(_ text:String) {
        title.text = text
    }
    
    public func setCompletion(_ errorBlock:@escaping () -> Void) {
        errorRetryBlock = errorBlock
    }
    
    @IBAction func retryButtonClicked(_ sender: Any) {
        errorRetryBlock()
    }
}

extension ErrorView: UpdateStateProtocol {
    func shouldUpdateView(_ state: ViewStateEnum) {
        switch state {
        case let .error(message):
            setErrorMessage(message)
            isHidden = false
            break
        default:
            isHidden = true
        }
    }
}
