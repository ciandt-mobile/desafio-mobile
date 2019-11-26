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

class MoviesGalleryViewController: UIViewController {

    @IBOutlet weak var segmentedControlTitle: UISegmentedControl!
    @IBOutlet weak var titleLabel: UILabel! {
        didSet{
            titleLabel.text =  "Upcoming Movies"
        }
    }
    @IBOutlet weak var galleryCollectionView: UICollectionView!
    
    var galleryMovies: [GalleryMovies] = [] {
        didSet {
            self.galleryCollectionView.reloadData()
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setupCollection()        
        parseGalleryMovies()
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
        case 1:
            titleLabel.text =  "Popular Movies"
        default:
            break
        }
    }
}
