//
//  MovieDetailsViewController.swift
//  MoviesApp
//
//  Created by Luan Audibert on 11/18/19.
//  Copyright © 2019 Luan Audibert. All rights reserved.
//

import UIKit
import RxCocoa
import RxSwift

class MovieDetailsViewController: UIViewController {
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var titleYearLabel: UILabel!
    @IBOutlet weak var lengthGenreLabel: UILabel!
    
    @IBOutlet weak var actorsCollectionView: UICollectionView!
    
    @IBOutlet weak var synopsisLabel: UITextView!
    var movie: MovieResult!
    var viewModel: MovieDetailsViewModel!
    
    private let disposeBag = DisposeBag()
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationBar()
        setupImageView()
        setupTitleYearLabel()
        setupSubscriptions()
        self.synopsisLabel.text = movie.overview
        self.lengthGenreLabel.text = "Loading Genres..."
        viewModel.getGenre(movie: movie)
    }
    private func setupNavigationBar() {
        // Clear navigation bar
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: .default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        self.navigationController?.navigationBar.isTranslucent = true
        self.navigationController?.view.backgroundColor = .clear
        
        let backButton = UIBarButtonItem(title: "", style: .plain, target: self, action: nil)
        backButton.tintColor = .lightGray
        self.navigationController?.navigationBar.topItem?.backBarButtonItem = backButton
    }
    
    private func setupImageView() {
        imageView.kf.indicatorType = .activity
        let url = URL(string: "https://image.tmdb.org/t/p/original\(movie.posterPath)")
        imageView.kf.setImage(with: url)
    }
    
    private func setupTitleYearLabel() {
        let year = Calendar.current.component(.year, from: movie.releaseDate)
    
        let attributedString = NSMutableAttributedString()
        attributedString.append(
            NSAttributedString(
                string: movie.title,
                attributes: [
                    .foregroundColor : UIColor.white,
                    .font : UIFont.boldSystemFont(ofSize: 23)
                ]
        )
        )
        attributedString.append(
            NSAttributedString(
                string: "   \(year)",
                attributes: [
                    .foregroundColor : UIColor.darkGray,
                    .font : UIFont.boldSystemFont(ofSize: 14)
                ]
            )
        )
        self.titleYearLabel.attributedText = attributedString
    }
    
    private func setupSubscriptions() {
        viewModel.genres.filterNil().drive(onNext: { (genres) in
            guard genres.count > 0 else {
                self.lengthGenreLabel.text = "Error loading genres"
                self.lengthGenreLabel.textColor = .red
                return
            }
            var genresString = ""
            for i in 0..<genres.count {
                genresString += "\(genres[i].name)\((i == genres.count-1) ? "" : ", ")"
            }
            self.lengthGenreLabel.text = genresString
            self.lengthGenreLabel.textColor = .darkGray
        }).disposed(by: self.disposeBag)
    }
}


