//
//  ViewController.swift
//  moviesTest
//
//  Created by Pedro  Apolloni on 14/12/19.
//  Copyright © 2019 Henrique Augusto. All rights reserved.
//

import UIKit
import Alamofire

class MovieDetailViewController: UIViewController {

    override func viewDidLoad() {
        fetchMovieDetail(movie?.id)
        super.viewDidLoad()
        
    }
    
    var movie: Movie!
    
    @IBOutlet weak var detailImage: UIImageView!
    @IBOutlet weak var detailTitle: UILabel!
    @IBOutlet weak var detailYear: UILabel!
    @IBOutlet weak var detailDescription: UILabel!
    
    
    private func fetchMovieDetail(_ id: Int?) {
        guard let movieId = movie?.id else { return }
        
        Alamofire.request(movieDetailUrl(movieId)).responseJSON { response in
            guard let data = response.data else { return }
            
            do {
                let info = try JSONDecoder().decode(Detail.self, from: data)
                self.buildMovieDetail(info)
                self.buildImageMovie(info)
                
            } catch { print(error) }
        }
    }
    
    private func buildMovieDetail(_ detail: Detail) {
        self.detailTitle.text = detail.title
        self.detailYear.text = detail.release_date
        self.detailDescription.text = detail.overview
    }
    
    private func buildImageMovie(_ detail: Detail) {
        guard let url = URL(string: buildImageUrl(detail.backdrop_path)) else { return }
        self.detailImage.kf.setImage(with: url)
    }
    
    private func movieDetailUrl(_ movieId: Int) -> String {
        return "https://api.themoviedb.org/3/movie/\(movieId)?api_key=0057d113575f47dd575af12137814abb"
    }
    
    private func buildImageUrl(_ url: String) -> String {
        return "https://image.tmdb.org/t/p/w400/\(url)"
    }
}
