//
//  ViewController.swift
//  MovieCatalog
//
//  Created by Vitor Antonio Terzariol Terribile on 17/11/19.
//  Copyright © 2019 Vitor Antonio Terzariol Terribile. All rights reserved.
//

import UIKit
import Alamofire
import SDWebImage

class ViewController: UIViewController, UICollectionViewDataSource, UICollectionViewDelegate {
    
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    @IBAction func selectorChanged(_ sender: Any) {
        switch segmentedControl.selectedSegmentIndex
        {
        case 0:
            getMovies(movieType: "upcoming")
        case 1:
            getMovies(movieType: "popular")
        default:
            break
        }
    }
    @IBOutlet weak var collectionView: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        getMovies(movieType: "upcoming")

    }

    func getMovies(movieType: String) {

        Alamofire.request(URL(string: "https://api.themoviedb.org/3/movie/\(movieType)?api_key=8eec7e37a8adda246d23680f9e6c6c1b")!)
            .validate()
            .response { (response) in

                if let data = response.data {
                    do {
                        try JSONSerialization.jsonObject(with: data, options: [])

                        let decoder = JSONDecoder()
                        let movies = try decoder.decode(Response.self, from: data)

                        self.items = movies.results
                        self.collectionView.reloadData()
                    } catch {
                        print("Error: ", error)
                    }
                }
            }
    }

    let reuseIdentifier = "cell"
    var items: [Movie] = []

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.items.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {

        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath as IndexPath) as! CollectionViewCell
        cell.movieTitle.text = self.items[indexPath.item].originalTitle
        let releaseYear = self.items[indexPath.item].releaseDate
        cell.movieYear.text = releaseYear
        cell.movieImage.sd_setImage(with: URL(string: "https://image.tmdb.org/t/p/original\(self.items[indexPath.item].posterPath)"), placeholderImage: UIImage(named: "placeholder.png"))

        return cell
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        performSegue(withIdentifier: "MovieDetailsSegue", sender: nil)
        print(items[indexPath.item].movieId!)
        movieIdSelected.movieId = items[indexPath.item].movieId!
    }
    struct movieIdSelected {
        static var movieId = Int()
    }
}

