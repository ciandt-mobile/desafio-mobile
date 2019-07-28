//
//  ViewController.swift
//  Movie
//
//  Created by Gabriel Guerrero on 25/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import UIKit

class MoviesListViewController: BaseViewController {
    
    //MARK: - @IBOutlet's
    @IBOutlet weak var moviesCollectionView: UICollectionView!
    @IBOutlet weak var categoryMovieLabel: UILabel!
    
    //MARK: - @IBAction's
    @IBAction func onSegmentedControlChanged(_ sender: UISegmentedControl) {
        moviesList = []
        currentPage = 1
        moviesCollectionView.reloadData()
        switch sender.selectedSegmentIndex {
        case 0:
            showLoadingAlert() {
                self.loadUpComingMoviesList()
            }
            DispatchQueue.main.async {
                self.categoryMovieLabel.text = "Upcoming Movies"
            }
        case 1:
            showLoadingAlert() {
                self.loadPopularMoviesList()
            }
            DispatchQueue.main.async {
                self.categoryMovieLabel.text = "Popular Movies"
            }
        default:
            print("unrecognized index of segmented control")
        }
        
    }
    
    //MARK: - Private Var's
    private var moviesList: [Movies] = []
    private var isLoadingMovies = false
    private var currentPage = 1
    private var total = 0
    private var idMovieSelected = 0
    
    //MARK: - Status Bar Style
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    //MARK: - View Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        showLoadingAlert {
            self.loadUpComingMoviesList()
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setupNavigationBar()
    }
    
    //MARK: - Private Methods
    private func setupNavigationBar() {
        navigationController?.isNavigationBarHidden = true
    }
    
    private func loadUpComingMoviesList() {
        isLoadingMovies = true
        
        APIClient.upComing(page: self.currentPage) { [unowned self] result in
            switch result {
            case .success(let moviesList):
                self.total = (moviesList?.total_pages)!
                self.moviesList += (moviesList?.results)!
                DispatchQueue.main.async {
                    self.moviesCollectionView.reloadData()
                }
            case .failure(let error):
                switch error {
                case .notFound:
                    self.showErrorAlert(title: "ERROR!", message: "Server communicatino failed!")
                case .unauthorized:
                    self.showErrorAlert(title: "ERROR!", message: "Server communication error!")
                case .unreconized:
                    self.showErrorAlert(title: "Something went wrong", message: "Try again later")
                }
            }
            self.isLoadingMovies = false
            self.dismissLoadingAlert()
        }
    }
    
    private func loadPopularMoviesList() {
        isLoadingMovies = true
        
        APIClient.popular(page: self.currentPage) { [unowned self] result in
            switch result {
            case .success(let moviesList):
                self.total = (moviesList?.total_pages)!
                self.moviesList += (moviesList?.results)!
                DispatchQueue.main.async {
                    self.moviesCollectionView.reloadData()
                }
            case .failure(let error):
                switch error {
                case .notFound:
                    self.showErrorAlert(title: "ERROR!", message: "Server communicatino failed!")
                case .unauthorized:
                    self.showErrorAlert(title: "ERROR!", message: "Server communication error!")
                case .unreconized:
                    self.showErrorAlert(title: "Something went wrong", message: "Try again later")
                }
            }
            self.isLoadingMovies = false
            self.dismissLoadingAlert()
        }
    }
    
    //MARK: - Navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let dvc = segue.destination as? MovieDetailViewController {
            dvc.movieId = idMovieSelected
        }
    }
}

//MARK: - UICollectionView Delegate, DataSource
extension MoviesListViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return moviesList.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let moviesCell = collectionView.dequeueReusableCell(withReuseIdentifier: "movieCell", for: indexPath) as? MoviesListCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        moviesCell.setupMovieCell(with: moviesList[indexPath.row])
        
        return moviesCell
    }
    
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        if indexPath.row == moviesList.count - 10 && !isLoadingMovies && moviesList.count != total {
            currentPage += 1
            loadUpComingMoviesList()
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let movieSelected = moviesList[indexPath.row]
        idMovieSelected = movieSelected.id ?? 0
        performSegue(SegueID.toMovieDetails)
    }
}
