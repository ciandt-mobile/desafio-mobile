//
//  MovieDetailViewController.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 25/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit

class MovieDetailViewController: UIViewController {

    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var yearLabel: UILabel!
    @IBOutlet weak var timeAndCategoriesLabel: UILabel!
    @IBOutlet weak var castCollectionView: UICollectionView!
    @IBOutlet weak var descriptionTextView: UITextView!
    
    var image = UIImage()
    var movieID: Int?
    var cast: [Cast] = [] {
        didSet {
            castCollectionView?.reloadData()
        }
    }
    var movieDetail: MovieDetail? {
        didSet {
            setUp()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setupCollection()
        setInteractivePopGesture()

        getMovieDetail()
        getCredits()
    }

    func setupCollection() {
        castCollectionView.delegate = self
        castCollectionView.dataSource = self
        
        let nibName = UINib(nibName: "ImageCastCollectionViewCell", bundle:nil)
        castCollectionView.register(nibName, forCellWithReuseIdentifier: "castCell")
    }
    
    func setUp() {
        guard let movieDetail = movieDetail else { return }
        
        let url = URL(string: "https://image.tmdb.org/t/p/original/" + (movieDetail.backdropPath ?? ""))

        self.imageView?.kf.setImage(with: url)

        self.titleLabel?.text = movieDetail.originalTitle
        self.descriptionTextView?.text = movieDetail.overview
        self.yearLabel?.text = movieDetail.releaseDate?.getDate()?.getYearString()
    
        self.timeAndCategoriesLabel?.text =  movieDetail.timeAndCategories
    }
}
