//
//  MovieDetailsViewController.swift
//  Desafio CI&T
//
//  Created by Mateus Kamoei on 31/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import UIKit

class MovieDetailsViewController: UIViewController {

    private let reuseIdentifier = "CastMemberCell"
    
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var informationLabel: UILabel!
    @IBOutlet weak var textView: UITextView!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var castActivityIndicatorView: UIActivityIndicatorView!
    @IBOutlet weak var movieDetailsActivityIndicatorView: UIActivityIndicatorView!
    
    var viewModel: MovieDetailsViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.title = "Movie Details"
        self.informationLabel.text = ""
        self.textView.text = ""
        self.titleLabel.text = self.viewModel.movieTitle
    }
    
}

extension MovieDetailsViewController: MovieDetailsViewModelDelegate {
    
    func didFinishGettingMovieDetails(_ viewModel: MovieDetailsViewModel) {
        DispatchQueue.main.async {
            self.movieDetailsActivityIndicatorView.stopAnimating()
            self.informationLabel.text = self.viewModel.movieInformation
            self.textView.text = self.viewModel.overview
        }
    }
    
    func didFailGettingMovieDetails(_ viewModel: MovieDetailsViewModel, error: Error?) {
        self.presentTryAgainAlert(with: "An error occurred when loading the movie details") {
            self.viewModel.getMovieDetails()
        }
    }
    
    func didFinishGettingMovieCredits(_ viewModel: MovieDetailsViewModel) {
        DispatchQueue.main.async {
            self.castActivityIndicatorView.stopAnimating()
            self.tableView.reloadData()
        }
    }
    
    func didFailGettingMovieCredits(_ viewModel: MovieDetailsViewModel, error: Error?) {
        self.presentTryAgainAlert(with: "An error occurred when loading the movie cast") {
            self.viewModel.getMovieCredits()
        }
    }
    
    func didFinishGettingImage(_ viewModel: MovieDetailsViewModel) {
        guard let image = viewModel.image else { return }
        self.imageView.image = image
    }
    
}

extension MovieDetailsViewController: UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.viewModel.castCount
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: reuseIdentifier, for: indexPath) as! CastMemberTableViewCell
        cell.selectionStyle = .none
        self.viewModel.setCastInformation(on: cell, with: indexPath)
        return cell
    }
}

extension MovieDetailsViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 60
    }
}
