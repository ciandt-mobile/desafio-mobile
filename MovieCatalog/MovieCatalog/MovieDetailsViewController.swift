//
//  MovieDetailsViewController.swift
//  MovieCatalog
//
//  Created by Vitor Antonio Terzariol Terribile on 20/11/19.
//  Copyright © 2019 Vitor Antonio Terzariol Terribile. All rights reserved.
//

import UIKit
import Alamofire

class MovieDetailsViewController: UIViewController {

    @IBOutlet weak var movieBanner: UIImageView!
    @IBOutlet weak var movieTitleDetails: UILabel!


    override func viewDidLoad() {
        super.viewDidLoad()

    }

    override func viewDidAppear(_ animated: Bool) {
        getMoviesDetails(movieId: ViewController.movieIdSelected.movieId)
    }

    func getMoviesDetails(movieId: Int) {

        Alamofire.request(URL(string: "https://api.themoviedb.org/3/movie/\(movieId)?api_key=8eec7e37a8adda246d23680f9e6c6c1b")!)
            .validate()
            .response { (response) in

                if let data = response.data {
                    do {
                        try JSONSerialization.jsonObject(with: data, options: [])

                        let decoder = JSONDecoder()
                        let movieDetails = try decoder.decode(MovieDetails.self, from: data)

                        print(movieDetails)

                        self.movieBanner.sd_setImage(with: URL(string: "https://image.tmdb.org/t/p/original\(movieDetails.posterPath)"), placeholderImage: UIImage(named: "placeholder.png"))
                        self.movieTitleDetails.text? = movieDetails.originalTitle

                    } catch {
                        print("Error: ", error)
                    }
                }
        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
