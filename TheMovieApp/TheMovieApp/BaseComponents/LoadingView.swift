//
//  LoadingView.swift
//  CaixaUI
//
//  Created by Wilson Kim on 09/05/19.
//

import Foundation
import UIKit

class LoadingView:UIView {
    
    @IBOutlet var contentView: UIView!
    @IBOutlet weak var containerView: UIView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var messageLabel: UILabel!
    
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
        setupContainerView()
        setupLabel()
    }
    
    private func setupContainerView() {
        containerView.layer.cornerRadius = 16
        backgroundColor = UIColor.clear
    }
    
    private func setupLabel() {
        messageLabel.textColor = Colors.darkGray
    }
    
    public func setLoadingMessage(_ message:String?) {
        startLoading()
        messageLabel.text = message ?? "Carregando informações..."
    }
    
    public func startLoading() {
        activityIndicator.startAnimating()
    }
    
    public func stopLoading() {
        activityIndicator.stopAnimating()
    }
}

extension LoadingView: UpdateStateProtocol {
    func shouldUpdateView(_ state: ViewStateEnum) {
        switch state {
        case let .loading(message):
            setLoadingMessage(message)
            isHidden = false
            break
        default:
            stopLoading()
            isHidden = true
        }
    }
}
