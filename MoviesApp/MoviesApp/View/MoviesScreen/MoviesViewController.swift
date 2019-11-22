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

enum MoviesFilterType: Int {
    case upcoming, popular
}

class MoviesViewController: UIViewController {
    
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var collectionVIew: UICollectionView!
    
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    let disposeBag = DisposeBag()
    var viewModel: MoviesViewModel!
    
    private var movies = [MovieResult]()
    
    private var loading = true {
        didSet {
            activityIndicator.isHidden = !loading
            if loading {
                activityIndicator.startAnimating()
            } else {
                activityIndicator.stopAnimating()
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.title = "Movies"
        navigationController?.navigationBar.barTintColor = UIColor.black
        navigationController?.navigationBar.titleTextAttributes = [.foregroundColor: UIColor.white]
        self.view.backgroundColor = .black
        self.loading = true
        viewModel.getMovies()
        setupCollectionView()
        setupSubscriptions()
    }
    
    private func setupCollectionView() {
        
        self.collectionVIew.dataSource = self
        self.collectionVIew.delegate = self
        self.collectionVIew.register(R.nib.movieCollectionViewCell)
        self.collectionVIew.backgroundColor = .clear
    }
    
    private func setupSubscriptions() {

        Driver.combineLatest(
            self.viewModel.movies,
            self.viewModel.moviesFilterType.asDriver().distinctUntilChanged()
        ).drive(onNext: { (movies, filterType) in
            self.movies = movies.filter { (movie) -> Bool in
                let now = Date()
                let alreadyReleased = movie.releaseDate <= now
                switch filterType {
                case .upcoming:
                    return !alreadyReleased
                case .popular:
                    return alreadyReleased
                }
            }
            self.loading = false
            self.collectionVIew.reloadData()
        }).disposed(by: self.disposeBag)
        
        segmentedControl.rx.value.asDriver().drive(onNext: { (segmentedControl) in
            guard let state = MoviesFilterType(rawValue: segmentedControl) else { return }
            self.viewModel.moviesFilterType.accept(state)
            
        }).disposed(by: self.disposeBag)
        
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        
        if let flowLayout = self.collectionVIew.collectionViewLayout as? UICollectionViewFlowLayout {
            let width = self.collectionVIew.bounds.width/3.2
            flowLayout.itemSize = CGSize(width: width, height: width * 1.75)
        }
    }
}

extension MoviesViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        viewModel.userSelectedMovie(movie: movies[indexPath.item])
    }
}

extension MoviesViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movies.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: R.reuseIdentifier.moviesCollectionViewCell, for: indexPath)!
        let movie = movies[indexPath.item]
        cell.movieNameLabel.text = movie.title
        cell.setImage(imageUrl: movie.posterPath)
        cell.setDate(movie.releaseDate)
        return cell
    }
    
}
