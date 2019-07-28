//
//  MovieDetailViewController.swift
//  Movie
//
//  Created by Gabriel Guerrero on 26/07/19.
//  Copyright © 2019 Gabriel Guerrero. All rights reserved.
//

import UIKit
import Kingfisher

class MovieDetailViewController: BaseViewController {

    //MARK: - Private Var's
    private var movieDetail: MovieDetail?
    private var cast: [Cast] = []
    
    //MARK: - Public Var's
    public var movieId: Int = 0
    
    //MARK: - @IBOutlet's
    @IBOutlet weak var posterImageView: UIImageView!
    @IBOutlet weak var movieTitleLabel: UILabel!
    @IBOutlet weak var releaseYearLabel: UILabel!
    @IBOutlet weak var timeAndGenereLabel: UILabel!
    @IBOutlet weak var overviewLabel: UILabel!
    @IBOutlet weak var collectionView: UICollectionView!
    
    //MARK: - View Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        showLoadingAlert() {
            self.loadMovieDetails()
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        setupNavigationBar()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        navigationController?.isNavigationBarHidden = true
    }
    
    //MARK: - Private Method's
    private func setupNavigationBar() {
        navigationController?.isNavigationBarHidden = false
    }
    
    private func loadMovieDetails() {
        APIClient.getMovieDetails(movieId: movieId.description, completion: { (result) in
            switch result {
            case .success(let movieDetail):
                self.populateView(with: movieDetail)
                self.movieDetail = movieDetail
                self.cast += movieDetail?.credits?.cast ?? []
                DispatchQueue.main.async {
                    self.collectionView.reloadData()
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
            DispatchQueue.main.async {
                self.dismissLoadingAlert()
            }
        })
    }
    
    private func populateView(with movieDetail: MovieDetail?) {
        if let detail = movieDetail {
            DispatchQueue.main.async {
                self.posterImageView.kf.indicatorType = .activity
                if detail.backdrop_path == nil {
                    self.posterImageView.kf.setImage(with: URL(string: HTTP.baseImageURL185 + (detail.poster_path ?? "")))
                } else {
                    self.posterImageView.kf.setImage(with: URL(string: HTTP.baseImageURL500 + (detail.backdrop_path ?? "")))
                }
                
                self.movieTitleLabel.text = detail.title
                self.releaseYearLabel.text = detail.release_date?.getYearDate()
                var timeAndGenre = "\(detail.runtime?.description ?? "")m | "
                detail.genres?.forEach({ (genre) in
                    timeAndGenre += "\(genre.name ?? "") "
                })
                self.timeAndGenereLabel.text = timeAndGenre
                self.overviewLabel.text = detail.overview
            }
        }
        
    }

}

//MARK: - UICollectionView DataSource Delegate
extension MovieDetailViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        //added + 1 because first cell is the movie poster
        return cast.count + 1
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "movieDetailCell", for: indexPath) as? MovieDetailCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        cell.setupCell(with: movieDetail, and: indexPath)
        
        return cell
    }
    
}
