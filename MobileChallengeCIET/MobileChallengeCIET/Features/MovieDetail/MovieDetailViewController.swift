//
//  MovieDetailViewController.swift
//  MobileChallengeCIET
//
//  Created by Guilherme C Rosa on 22/07/19.
//  Copyright © 2019 Guilherme C Rosa. All rights reserved.
//

import UIKit

class MovieDetailViewController: UIViewController {

    private var movie: Movie?
    
    init(movie: Movie) {
        super.init(nibName: nil, bundle: nil)
        self.movie = movie
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = self.movie?.title
        self.navigationController?.setNavigationBarHidden(false, animated: true)
    }
}
