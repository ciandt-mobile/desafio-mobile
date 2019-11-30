//
//  ViewController.swift
//  movies
//
//  Created by Arthur Silva on 11/29/19.
//  Copyright © 2019 Arthur Silva. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var moviesTableView: UITableView!

    let movieService = MovieDbService()
    var movies: [Movie] = []

    override func viewDidLoad() {
        super.viewDidLoad()

        movieService.getPopularMovies(onSuccess: { popularMovies in
            self.movies = popularMovies.results
            self.moviesTableView.reloadData()
        }) {
            print("oh oh")
        }
        setupView()
    }

    func setupView() {

        let nib = UINib(nibName: PopularMoviesTableTableViewCell.cellIdentifier, bundle: nil)
        self.moviesTableView.register(nib, forCellReuseIdentifier: PopularMoviesTableTableViewCell.cellIdentifier)
    }
}

extension ViewController: UITableViewDelegate {
}

extension ViewController: UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.movies.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        guard let cell = tableView.dequeueReusableCell(withIdentifier: PopularMoviesTableTableViewCell.cellIdentifier, for: indexPath) as? PopularMoviesTableTableViewCell else {
            fatalError("Could not dequeue PopularMoviesTableViewCell")
        }

        let movie = self.movies[indexPath.row]
        cell.movieTitle.text = movie.title
        cell.voteAverageScoreLabel.text = String(movie.vote_average)
        if let releaseDateComponents = movie.releaseDateComponents, let releaseYear = releaseDateComponents.year {
            cell.yearLabel.text = String(releaseYear)
        }

        self.movieService.getImage(imagePath: movie.poster_path) { posterImage in
            cell.moviePoster.image = posterImage
        }

        return cell
    }


}
