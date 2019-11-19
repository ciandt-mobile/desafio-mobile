//
//  MovieDetailsViewModel.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/18/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation
import RxFlow
import RxSwift
import RxCocoa

class MovieDetailsViewModel: Stepper {
    
    var steps = PublishRelay<Step>()
    
    init() {
        print("hi?? 2")
    }
}
