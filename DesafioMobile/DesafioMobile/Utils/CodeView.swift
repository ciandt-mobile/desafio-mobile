//
//  CodeView.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

//MARK: - CodeView Protocol
protocol CodeView {
    func buildViewHierarchy()
    func setupConstrains()
    func setupAdditionalConfiguration()
    func setupView()
}

//MARK: - CodeView Setup
extension CodeView {
    func setupView(){
        buildViewHierarchy()
        setupConstrains()
        setupAdditionalConfiguration()
    }
}
