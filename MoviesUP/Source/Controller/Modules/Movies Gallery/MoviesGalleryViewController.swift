//
//  MovieViewController.swift
//  MoviesUP
//
//  Created by Julio Garavelli on 23/11/19.
//  Copyright © 2019 Julio Garavelli. All rights reserved.
//

import UIKit
import Alamofire
import Kingfisher

enum TypeMovies: Int {
    case upcoming = 0
    case popular = 1
    
    var title: String {
        switch self {
        case .upcoming:
            return "UPCOMING_MOVIES".localized()
        case .popular:
            return "POPULAR_MOVIES".localized()
        }
    }
}

class MoviesGalleryViewController: UIViewController {
    
    @IBOutlet weak var segmentedControlTitle: UISegmentedControl!{
        didSet {
           setupSegmentedControl()
        }
    }
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var galleryCollectionView: UICollectionView!
    
    var galleryMovies: [GalleryMovies] = [] {
        didSet {
            DispatchQueue.main.async {
                self.galleryCollectionView.reloadData()
            }
        }
    }
    
    var typeMovies: TypeMovies = .upcoming {
        didSet {
            titleLabel.text = typeMovies.title
            if typeMovies == .upcoming {
                parseGalleryMoviesUpcoming()
            } else {
                parseGalleryMoviesPopular()
            }
        }
    }

    var upcomingMovies: [GalleryMovies] = [] {
        didSet {
            self.galleryMovies = upcomingMovies
        }
    }

    var popularMovies: [GalleryMovies] = [] {
        didSet {
            self.galleryMovies = popularMovies
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupCollection()
        getData()
    }
    
    override func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {
        super.viewWillTransition(to: size, with: coordinator)
        self.galleryCollectionView.reloadData()
    }
    
    func getData() {
        titleLabel.text = typeMovies.title
        parseGalleryMoviesUpcoming()
    }
    
    func setupSegmentedControl() {
        segmentedControlTitle.backgroundColor = UIColor.black
        segmentedControlTitle.layer.borderColor = UIColor.white.cgColor
        if #available(iOS 13.0, *) {
            segmentedControlTitle.selectedSegmentTintColor = UIColor.white
        }
        segmentedControlTitle.layer.borderWidth = 1
        
        let titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
        segmentedControlTitle.setTitleTextAttributes(titleTextAttributes, for:.normal)
        
        let titleTextAttributes1 = [NSAttributedString.Key.foregroundColor: UIColor.black]
        segmentedControlTitle.setTitleTextAttributes(titleTextAttributes1, for:.selected)
        galleryCollectionView.reloadData()
        
        segmentedControlTitle.setTitle("UPCOMING".localized(), forSegmentAt: 0)
        segmentedControlTitle.setTitle("POPULAR".localized(), forSegmentAt: 1)
    }
    
    func setupCollection() {
        galleryCollectionView.delegate = self
        galleryCollectionView.dataSource = self
        
        let nibName = UINib(nibName: "ImageMovieCollectionViewCell", bundle:nil)
        galleryCollectionView.register(nibName, forCellWithReuseIdentifier: "cell")
    }
    
    @IBAction func indexChanged(_ sender: Any) {
        guard let typeMovies = TypeMovies(rawValue: segmentedControlTitle.selectedSegmentIndex) else { return }
        self.typeMovies = typeMovies
    }
}
