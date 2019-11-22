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
import SwinjectAutoregistration

class MoviesFlow {
    private let container: Container
    private lazy var rootViewController = container.resolve(UINavigationController.self, argument: true)!
    
    init(container parent: Container) {
        container = Container(parent: parent) { container in
            container.autoregister(MoviesViewModel.self, initializer: MoviesViewModel.init)
            container.register(MoviesViewController.self) { _ in
                let controller = MoviesViewController(nibName: R.nib.moviesViewController.name, bundle: nil)
                controller.viewModel = container ~> MoviesViewModel.self
                return controller
            }
            
            container.autoregister(MovieDetailsViewModel.self, initializer: MovieDetailsViewModel.init)
            container.register(MovieDetailsViewController.self) { _ in
                let controller = MovieDetailsViewController(nibName: R.nib.movieDetailsViewController.name, bundle: nil)
                controller.viewModel = container ~> MovieDetailsViewModel.self
                return controller
            }
        }
    }
    

    private func navigateToDetails(_ movie: MovieResult) -> FlowContributors {
        let viewController = container ~> MovieDetailsViewController.self
        
        viewController.movie = movie

        self.rootViewController.pushViewController(viewController, animated: true)
        return .one(flowContributor:
            FlowContributor.contribute(withNextPresentable: viewController, withNextStepper: viewController.viewModel)
        )
        
    }
    private func navigateToMovies() -> FlowContributors {
        let viewController = container ~> MoviesViewController.self
//        self.rootViewController.setViewControllers([viewController], animated: true)
        self.rootViewController.pushViewController(viewController, animated: true)
        return .one(flowContributor:
            FlowContributor.contribute(withNextPresentable: viewController, withNextStepper: viewController.viewModel)
        )
    }
}

extension MoviesFlow: Flow {
    var root: Presentable {
//        self.rootViewController.setNavigationBarHidden(true, animated: false)
        return self.rootViewController
    }
    
    func navigate(to step: Step) -> FlowContributors {
        guard let step = step as? MoviesState else { return .none }
        switch step {
        case .home:
            return navigateToMovies()
        case .dismiss:
            self.rootViewController.popViewController(animated: true)
            return .none
        case .details(let movie):
            return navigateToDetails(movie)
        }
    }
}

enum MoviesState: Step {
    case home
    case details(movie: MovieResult)
    case dismiss
}
