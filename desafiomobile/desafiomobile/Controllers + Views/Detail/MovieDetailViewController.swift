//
//  MovieDetailViewController.swift
//  desafiomobile
//
//  Created by Erasmo Oliveira on 21/07/19.
//  Copyright © 2019 Erasmo Oliveira. All rights reserved.
//

import UIKit
import Kingfisher

protocol MovieDetailDelegate {
    
}

class MovieDetailViewController: UIViewController {

    // MARK :- Outlets
    @IBOutlet weak var image: UIImageView!
    @IBOutlet weak var movieTitle: UILabel!
    @IBOutlet weak var year: UILabel!
    @IBOutlet weak var overview: UILabel!
    
    // MARK :- properties
    var movie: MovieDetail?
    
    // MARK :- Life Cycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        guard let movie = movie else { return }

        if let imageString = movie.imageString {
            
            let imagePath = "https://image.tmdb.org/t/p/w500" + imageString
            if let imageUrl = URL(string: imagePath) {
                self.image.kf.setImage(with: imageUrl)
            }
            
        }
        
        if let movieTitle = movie.name {
            self.movieTitle.text = movieTitle
        }
        
        if let year = movie.releaseDate {
            self.year.text = year
        }
        
        if let overview = movie.overview {
            self.overview.text = overview
        }

    }
    
    // MARK :- IBAction
    @IBAction func close(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }

}

struct MovieDetail {

    var name: String?
    var releaseDate: String?
    var overview: String?
    var imageString: String?
    
    init(dict: [String : Any]) {
        
        if let name = dict["title"] as? String {
            self.name = name
        }
        
        if let poster_path = dict["poster_path"] as? String {
            self.imageString = poster_path
        }
        
        if let releaseDate = dict["release_date"] as? String {
            self.releaseDate = releaseDate
        }
        
        if let overview = dict["overview"] as? String {
            self.overview = overview
        }
        
    }
    
}
