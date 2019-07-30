//
//  StateFullView.swift
//  CaixaUI
//
//  Created by Wilson Kim on 09/05/19.
//

import UIKit

public enum ViewStateEnum {
    case loading(_ loadingMessage:String?)
    case error(_ errorMessage:String?)
    case empty(_ emptyMessage:String?, image:UIImage?)
    case normalLayout
}

protocol UpdateStateProtocol {
    func shouldUpdateView(_ state:ViewStateEnum)
}

public class StateFullView: UIView {
    
    var viewsDict:[String:UpdateStateProtocol] = [:]
    var errorView = ErrorView()
    var loadingView = LoadingView()
    var emptyView = EmptyView()
    
    private var state:ViewStateEnum = .normalLayout {
        didSet {
            updateViewState()
        }
    }
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        commonInit()
    }
    
    private func commonInit() {
        setup()
    }
    
    private func setup() {
        setupDictionary()
        setupErrorView()
        setupLoadingView()
        setupEmptyView()
    }
    
    private func setupDictionary() {
        viewsDict[String(describing: ErrorView.self)] = errorView
        viewsDict[String(describing: LoadingView.self)] = loadingView
        viewsDict[String(describing: EmptyView.self)] = emptyView
    }
    
    private func setupErrorView() {
        addSubview(errorView)
        errorView.translatesAutoresizingMaskIntoConstraints = false
        errorView.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 0).isActive = true
        errorView.topAnchor.constraint(equalTo: self.topAnchor, constant: 0).isActive = true
        errorView.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: 0).isActive = true
        errorView.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: 0).isActive = true
        errorView.isHidden = true
    }
    
    private func setupLoadingView() {
        addSubview(loadingView)
        loadingView.translatesAutoresizingMaskIntoConstraints = false
        loadingView.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 0).isActive = true
        loadingView.topAnchor.constraint(equalTo: self.topAnchor, constant: 0).isActive = true
        loadingView.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: 0).isActive = true
        loadingView.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: 0).isActive = true
        loadingView.isHidden = true
    }
    
    private func setupEmptyView() {
        addSubview(emptyView)
        emptyView.translatesAutoresizingMaskIntoConstraints = false
        emptyView.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 0).isActive = true
        emptyView.topAnchor.constraint(equalTo: self.topAnchor, constant: 0).isActive = true
        emptyView.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: 0).isActive = true
        emptyView.bottomAnchor.constraint(equalTo: self.bottomAnchor, constant: 0).isActive = true
        emptyView.isHidden = true
    }
    
    private func updateViewState() {
        for value in viewsDict.values {
            value.shouldUpdateView(state)
        }
    }
    
    public func setState(_ state:ViewStateEnum) {
        self.state = state
    }
    
    public func setErrorCompletion(_ errorBlock:@escaping () -> Void) {
        errorView.setCompletion {
            errorBlock()
        }
    }
}
