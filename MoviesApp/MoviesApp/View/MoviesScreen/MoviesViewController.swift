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
        navigationController?.title = "Test"
        navigationItem.title = "testando"
        print("alo?")
        
        let view = UIView(frame: CGRect(x: 100, y: 100, width: 100, height: 100))
        view.backgroundColor = .red
        
        self.view.addSubview(view)
    
        view.rx.tapGesture().when(.recognized).subscribe(onNext: { _ in
            self.viewModel.tap()
        }).disposed(by: self.disposeBag)
    }

}
