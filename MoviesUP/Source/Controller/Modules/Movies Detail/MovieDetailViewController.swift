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
        
        setcustomBackImage()
        setupCollection()
        setInteractivePopGesture()

        getMovieDetail()
        getCredits()
    }
    
     override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: true)
    }

    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated:true)
    }
    
    func setcustomBackImage() {
        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
        self.navigationController?.navigationBar.isTranslucent = true
        navigationController?.navigationBar.backIndicatorImage = UIImage(named: "back-yellow")
        navigationController?.navigationBar.backIndicatorTransitionMaskImage = UIImage(named: "back-yellow")
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
