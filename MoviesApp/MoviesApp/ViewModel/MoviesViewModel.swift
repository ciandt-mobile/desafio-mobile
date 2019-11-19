//
//  MoviesViewModel.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/14/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation
import RxSwift
import RxCocoa
import RxFlow

class MoviesViewModel: Stepper {
    
    var steps = PublishRelay<Step>()
    
    let movies = BehaviorRelay<String>(value: "Test")
    init() {
        print("hi??")
    }
    
    func tap() {
        self.steps.accept(MoviesState.details)
    }
}
