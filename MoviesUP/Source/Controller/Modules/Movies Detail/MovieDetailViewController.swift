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
    @IBOutlet weak var descriptionLabel: UILabel!
    
    var image = UIImage()
    var movieID: Int?
    var movieDetail: MovieDetail? {
        didSet {
            setUp()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        setupCollection()
        getMovieDetail()
    }
    
    func setupCollection() {
        castCollectionView.delegate = self
        castCollectionView.dataSource = self
        
        let nibName = UINib(nibName: "ImageCastCollectionViewCell", bundle:nil)
        castCollectionView.register(nibName, forCellWithReuseIdentifier: "castCell")
    }
    
    func setUp() {
        guard let movieDetail = movieDetail else { return }
//        self.imageView?.image =

        self.titleLabel?.text = movieDetail.originalTitle
        self.descriptionLabel?.text = movieDetail.overview
        self.yearLabel?.text = movieDetail.releaseDate?.getDate()?.getYearString()
    
        self.timeAndCategoriesLabel?.text =  movieDetail.timeAndCategories
    }
}

extension MovieDetailViewController {
    func getMovieDetail() {
        guard let movieID = movieID else { return }

        ServiceManager().getMovieDetail(movieId: movieID) { (detail, error) in
            guard let detail = detail else {
                return
            }
            self.movieDetail = detail
        }
    }
}
