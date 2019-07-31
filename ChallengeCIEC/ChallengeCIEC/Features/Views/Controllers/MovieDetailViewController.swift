//
//  MovieDetailViewController.swift
//  ChallengeCIEC
//
//  Created by Guilherme Camilo Rosa on 25/07/19.
//  Copyright © 2019 Guilherme Rosa. All rights reserved.
//

import UIKit

class MovieDetailViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    
    private var movie: Movie!
    private var presenter = MovieDetailPresenter()
    
    init(movie: Movie) {
        super.init(nibName: nil, bundle: nil)
        self.movie = movie
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupPresenter()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        self.navigationController?.navigationBar.tintColor = .darkGray
        self.title = "details"
    }
    
    private func setupPresenter() {
        self.presenter = MovieDetailPresenter()
        self.presenter.delegate = self
        self.presenter.fetchMovieDetail(movie: self.movie)
    }
    
    private func setupTableView(builder: MovieDetailTableBuilder){
        builder.tableView = self.tableView
        builder.delegate = self
        
        self.tableView.dataSource = builder
        self.tableView.delegate = builder
        self.tableView.reloadData()
    }
}

extension MovieDetailViewController: MovieDetailTableBuilderProtocol {
    
}

extension MovieDetailViewController: MovieDetailPresenterProtocol {
    func didUpdateBuilder(builder: MovieDetailTableBuilder) {
        self.setupTableView(builder: builder)
    }
}
