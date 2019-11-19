//
//  MoviesViewController.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/14/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import RxGesture

class MoviesViewController: UIViewController {
    
    let disposeBag = DisposeBag()
    var viewModel: MoviesViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationItem.title = "testando"
        viewModel.getMovies()
        
        viewModel.movies.filterNil().drive(onNext: { (movies) in
            movies.map { $0.title }.forEach {
                print("\($0)")
            }
        }).disposed(by: self.disposeBag)
    }

}
