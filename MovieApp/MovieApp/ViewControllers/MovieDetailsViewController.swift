//
//  MovieDetailsViewController.swift
//  MovieApp
//
//  Created by Michele de Olivio Corrêa on 19/07/19.
//  Copyright © 2019 Michele de Olivio Corrêa. All rights reserved.
//

import Foundation
import UIKit
class MovieDetailsViewController: UIViewController {
    @IBOutlet var uiScrollView: UIScrollView?

    @IBOutlet weak var uiCastCollectionViewHeight: NSLayoutConstraint?
    @IBOutlet weak var uiGenresHeight: NSLayoutConstraint?
    @IBOutlet weak var uiGeneralInfoHeight: NSLayoutConstraint?
    
    @IBOutlet weak var uiLoadingIndicator: UIActivityIndicatorView?
    @IBOutlet weak var uiView: UIView?
    @IBOutlet weak var uiGenres: UILabel?
    @IBOutlet weak var uiMainImage: UIImageView?
    @IBOutlet weak var uiTitle: UILabel?
    @IBOutlet weak var uiGeneralInfo: UILabel?
    @IBOutlet weak var uiCastCollectionView: UICollectionView?
    @IBOutlet weak var uiOverview: UILabel?
    
    public var movieId: Int?

    private var movieCast: [Movie.MovieCast] = []

    private var movie: Movie? {
        didSet {
            updateMovieDetail()
        }
    }
    
    private func updateScrollContent() {
        let height = view.frame.size.height + 100
        let width = view.frame.size.width

        uiScrollView?.contentSize = CGSize(width: width, height: height)
    }
    
    override func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {

        fetchMovieDetail()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.accessibilityIdentifier = "movie_details"
        uiView?.accessibilityIdentifier = "movie_details"
        
        updateScrollContent()

        setupCollectionView()
        
        fetchMovieDetail()
    }
    
    private func setupCollectionView() {
        uiCastCollectionView?.register(UINib(nibName: "CastCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: "castCollectionViewCell")
        uiCastCollectionView?.delegate = self
        uiCastCollectionView?.dataSource = self
    }
    
    private func fetchMovieDetail() {
        uiLoadingIndicator?.startAnimating()
        uiLoadingIndicator?.isHidden = false

        guard let movieId = movieId else {
            showErrorAlert()
            return
        }
        
        MovieStore.shared.fetchMovie(with: movieId, successHandler: {[weak self] (movie) in
            self?.movie = movie
        }) { [weak self] (error) in
            self?.showErrorAlert()
        }
    }
    
    private func showErrorAlert() {
        let action = UIAlertAction(title: "Ok", style: UIAlertAction.Style.default, handler: { _ in
            self.navigationController?.popViewController(animated: true)
        })
        
        let alert = UIAlertController(title: "Erro", message: "Este filme não está disponível.", preferredStyle: UIAlertController.Style.alert)
        alert.addAction(action)
        
        present(alert, animated: true, completion: nil)
    }
    
    private func updateMovieDetail() {
        uiLoadingIndicator?.stopAnimating()
        uiLoadingIndicator?.isHidden = true
        
        guard let movie = movie else {
            showErrorAlert()
            return
        }
        
        uiMainImage?.image = UIImage(named: "AppIcon")
        uiMainImage?.downloaded(from: movie.backdropURL)
        
        uiTitle?.text = movie.title
        
        setupGeneralInfo(for: movie)
        setupGenres(for: movie)
        setupCollectionViewElements(for: movie)
        
        uiOverview?.text = movie.overview
        uiOverview?.sizeToFit()
    }
    
    private func setupGenres(for movie: Movie) {
        
        guard let rawGenres = movie.genres else {
            uiGenresHeight?.constant = 0
            return
        }
        
        var genres: [String] = []
        
        rawGenres.forEach {
            genres.append($0.name)
        }
        
        uiGenres?.text = genres.joined(separator: ", ")
    }
    
    private func setupCollectionViewElements(for movie: Movie) {
        
        guard let cast = movie.credits?.cast else {
            uiCastCollectionViewHeight?.constant = 0
            return
        }
        
        cast.forEach {
            if $0.profilePath != nil {
                movieCast.append($0)
            }
        }
        
        uiCastCollectionView?.reloadData()
    }
    
    private func setupGeneralInfo(for movie: Movie) {
        var text: [String] = []

        if let year = DateHelper.shared.year(from: movie.releaseDate) {
            text.append(String(year))
        }
        
        if let time = movie.runtime {
             text.append("\(time)m")
        }
        
        guard !text.isEmpty else {
            uiGeneralInfoHeight?.constant = 0
            return
        }
        
        uiGeneralInfo?.text = text.joined(separator: " | ")
    }
}

extension MovieDetailsViewController: UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movieCast.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "castCollectionViewCell", for: indexPath) as! CastCollectionViewCell
        
        let movie = movieCast[indexPath.item]
        
        cell.setup(actor: movie)
        return cell
    }
}
