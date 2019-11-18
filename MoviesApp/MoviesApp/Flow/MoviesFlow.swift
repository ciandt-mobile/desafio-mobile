//
//  MoviesFlow.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/18/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import Foundation
import RxFlow
import RxCocoa
import Swinject


enum MoviesState: Step {
    case home
    case details
}
