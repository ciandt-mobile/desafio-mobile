//
//  MovieDetailsViewController.swift
//  MoviesChallenge
//
//  Created by Leonardo Bortolotti on 14/12/19.
//  Copyright © 2019 Leonardo Bortolotti. All rights reserved.
//

import UIKit

class MovieDetailsViewController: UIViewController, UICollectionViewDataSource {
    
    @IBOutlet weak var bannerImageView: UIImageView!
    @IBOutlet weak var bannerHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var yearLabel: UILabel!
    @IBOutlet weak var genreLabel: UILabel!
    @IBOutlet weak var castCollectionView: UICollectionView!
    @IBOutlet weak var descriptionLabel: UILabel!
    
    let kCAST_CELL = "CastCell"
    let kBANNER_HEIGHT: CGFloat = 300
    
    var id: Int!
    private var movieDetails: MovieDetails!
    private var movieCredits: MovieCredits!
    
    private var activityIndicator: UIActivityIndicatorView!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Adiciona Activity Indicator enquanto está carregando
        activityIndicator = UIActivityIndicatorView(style: .large)
        activityIndicator.color = .white
        activityIndicator.center = self.view.center
        self.view.addSubview(activityIndicator)
        activityIndicator.startAnimating()
        
        castCollectionView.register(UINib(nibName: "CastCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: kCAST_CELL)

        changeBannerHeight()
        requestDetails()
        requestCast()
    }
    
    override func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {
        super.viewWillTransition(to: size, with: coordinator)
        changeBannerHeight()
    }
    
    // MARK: - Button Pressed
    
    @IBAction func backButtonPressed(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
    
    // MARK: - Helper Methods
    
    private func requestDetails() {
        MovieAPI.requestMovieDetails(id: id) { (data) in
            if let data = data {
                do {
                    self.movieDetails = try JSONDecoder().decode(MovieDetails.self, from: data)
                    DispatchQueue.main.async {
                        self.activityIndicator.removeFromSuperview()
                        self.setupUserInterface()
                    }
                } catch let error {
                    print(error)
                }
            }
        }
    }
    
    private func requestCast() {
        MovieAPI.requestMovieCast(id: id) { (data) in
            if let data = data {
                do {
                    self.movieCredits = try JSONDecoder().decode(MovieCredits.self, from: data)
                    print(self.movieCredits!)
                    DispatchQueue.main.async {
                        self.castCollectionView.dataSource = self
                        self.castCollectionView.reloadData()
                    }
                } catch let error {
                    print(error)
                }
            }
        }
    }
    
    private func setupUserInterface() {
        bannerImageView.kf.setImage(with: URL(string: Constants.kIMAGES_URL + (movieDetails.backdropPath ?? "")))
        titleLabel.text = movieDetails.title
        
        // Cria DateFormatter para extrair o ano da data
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let date = dateFormatter.date(from: movieDetails.releaseDate ?? "")
        dateFormatter.dateFormat = "yyyy"
        yearLabel.text = dateFormatter.string(from: date!)
        
        var genresArray: [String] = []
        for genre in movieDetails.genres! {
            genresArray.append(genre.name!)
        }
        genreLabel.text = "\(movieDetails.runtime ?? 0)m | \(genresArray.joined(separator: ", "))"
        
        descriptionLabel.text = movieDetails.overview
    }
    
    // Mudar o tamanho do Banner quando Landscape, para aparecer a label de descrição (apenas quando iPhone)
    private func changeBannerHeight() {
        if UIDevice.current.orientation.isLandscape && UIDevice.current.userInterfaceIdiom == .phone {
            bannerHeightConstraint.constant = 80
        } else {
            bannerHeightConstraint.constant = kBANNER_HEIGHT
        }
    }
    
    // MARK: - UICollectionViewDataSource
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movieCredits.cast?.count ?? 0
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let actor = movieCredits.cast![indexPath.row]
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: kCAST_CELL, for: indexPath) as! CastCollectionViewCell
        cell.actorImageView.kf.setImage(with: URL(string: Constants.kIMAGES_URL + (actor.profilePath ?? "")))
        cell.nameLabel.text = actor.name
        cell.characterLabel.text = actor.character
        return cell
    }

}
