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

enum Movies {
    case upcoming
    case popular
}

class MoviesGalleryViewController: UIViewController {
    
    @IBOutlet weak var segmentedControlTitle: UISegmentedControl!{
        didSet {
            segmentedControlTitle.backgroundColor = UIColor.black
            segmentedControlTitle.layer.borderColor = UIColor.white.cgColor
            if #available(iOS 13.0, *) {
                segmentedControlTitle.selectedSegmentTintColor = UIColor.white
            } else {
                // Fallback on earlier versions
            }
            segmentedControlTitle.layer.borderWidth = 1
            
            let titleTextAttributes = [NSAttributedString.Key.foregroundColor: UIColor.white]
            segmentedControlTitle.setTitleTextAttributes(titleTextAttributes, for:.normal)
            
            let titleTextAttributes1 = [NSAttributedString.Key.foregroundColor: UIColor.black]
            segmentedControlTitle.setTitleTextAttributes(titleTextAttributes1, for:.selected)
            galleryCollectionView.reloadData()
        }
    }
    @IBOutlet weak var titleLabel: UILabel! {
        didSet{
            titleLabel.text =  "Upcoming Movies"
        }
    }
    @IBOutlet weak var galleryCollectionView: UICollectionView!
    
    var galleryMovies: [GalleryMovies] = [] {
        didSet {
            DispatchQueue.main.async {
                self.galleryCollectionView.reloadData()
            }
        }
    }
    
    var typeMovies: Movies?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupCollection()
        
        switch segmentedControlTitle.selectedSegmentIndex {
        case 0:
            parseGalleryMoviesUpcoming()
        case 1:
            parseGalleryMoviesPopular()
        default:
            break
        }
    }
    
    func setupCollection() {
        galleryCollectionView.delegate = self
        galleryCollectionView.dataSource = self
        
        let nibName = UINib(nibName: "ImageMovieCollectionViewCell", bundle:nil)
        galleryCollectionView.register(nibName, forCellWithReuseIdentifier: "cell")
    }
    
    @IBAction func indexChanged(_ sender: Any) {
        switch segmentedControlTitle.selectedSegmentIndex {
        case 0:
            titleLabel.text =  "Upcoming Movies"
            self.typeMovies = .upcoming
            parseGalleryMoviesUpcoming()
        case 1:
            titleLabel.text =  "Popular Movies"
            self.typeMovies = .popular
            parseGalleryMoviesPopular()
        default:
            break
        }
    }
}
