//
//  UpcomingTableViewController.swift
//  moviesTest
//
//  Created by Pedro  Apolloni on 14/12/19.
//  Copyright © 2019 Henrique Augusto. All rights reserved.
//

import UIKit
import Alamofire
import Kingfisher

class MoviesTableViewControler: UITableViewController {
    
    var movies: [Movie] = []
    var page = 1
    var movieType: String = "upcoming"
    
    override func viewDidLoad() {
        tableView.rowHeight = 120
        fetchUpcomingMovies()
        super.viewDidLoad()
    }
    
    
    @IBAction func upcomingButton(_ sender: Any) {
        self.movies.removeAll()
        self.movieType = "upcoming"
        self.page = 1
        fetchUpcomingMovies()
    }
    
    @IBAction func popularButton(_ sender: Any) {
        self.movies.removeAll()
        self.movieType = "popular"
        self.page = 1
        fetchPopularMovies()
        self.tableView.reloadData()
    }
    
    func fetchUpcomingMovies() {
        Alamofire.request(upcomingUrl()).responseJSON { response in
            guard let data = response.data else { return }
            
            do {
                let info = try JSONDecoder().decode(Upcoming.self, from: data)
                self.movies += info.results
                DispatchQueue.main.async {
                    self.tableView.reloadData()
                }
            } catch { print(error) }
            
        }
    }
    
    func fetchPopularMovies() {
        Alamofire.request(popularUrl()).responseJSON { response in
            guard let data = response.data else { return }
            
            do {
                let info = try JSONDecoder().decode(Upcoming.self, from: data)
                self.movies += info.results
                
            } catch { print(error) }
        }
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movies.count
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! MoviesCells
        
        let movie = movies[indexPath.row]
        
        if let url = URL(string: buildImageUrl(movie.backdrop_path ?? "")){
            cell.backdropPath?.kf.setImage(with: url)
        }
        cell.title?.text = movie.title
        cell.releaseDate?.text = movie.release_date
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        self.page += 1
        if self.movieType == "upcoming" {
            fetchUpcomingMovies()
        } else {
            fetchPopularMovies()
        }
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let detail = segue.destination as! MovieDetailViewController
        detail.movie = movies[tableView.indexPathForSelectedRow!.row]
    }
    
    private func upcomingUrl() -> String {
        return "https://api.themoviedb.org/3/discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22&api_key=0057d113575f47dd575af12137814abb&page=\(page)"
    }
    
    private func popularUrl() -> String {
        return "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=0057d113575f47dd575af12137814abb&page=\(page)"
    }
    
    private func buildImageUrl(_ url: String) -> String {
        return "https://image.tmdb.org/t/p/w500/\(url)"
    }
}

class MoviesCells: UITableViewCell {
    
    @IBOutlet weak var title: UILabel?
    @IBOutlet weak var releaseDate: UILabel?
    @IBOutlet weak var backdropPath: UIImageView?
    
}
