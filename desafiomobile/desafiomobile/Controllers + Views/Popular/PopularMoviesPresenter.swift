//
//  PopularMoviesPresenter.swift
//  desafiomobile
//
//  Created by Erasmo Oliveira on 21/07/19.
//  Copyright © 2019 Erasmo Oliveira. All rights reserved.
//

import UIKit
import SVProgressHUD

protocol PopularMoviesProtocol {
    func reloadView()
}

class PopularMoviesPresenter: NSObject {
    
    // MARK :- vars
    var delegate: PopularMoviesProtocol?
    var movies = [MovieDetail]()
    
    func viewDidLoad() {
        loadMovies()
    }
    
    func numberOfItemsInSection() -> Int {
        return movies.count
    }
    
    func convertoToMovies(data: Data) {
        guard let response = try? JSONSerialization.jsonObject(with: data, options: []) as? [String : Any] else { return }
        guard let results = response["results"] as? [[String : Any]] else { return }
        
        for movie in results {
            movies.append(MovieDetail(dict: movie))
        }
        
        delegate?.reloadView()
    }
    
    func loadMovies() {
        
        guard let postData = "{}".data(using: .utf8) else { return }
        
        guard let url = URL(string: "https://api.themoviedb.org/3/movie/popular?page=1&language=en-US&api_key=f3457e0e73837d80da2de63f310ea930") else { return }
        var request = URLRequest(url: url,
                                 cachePolicy: .useProtocolCachePolicy,
                                 timeoutInterval: 10.0)
        request.httpMethod = "GET"
        request.httpBody = postData
        
        let session = URLSession.shared
        
        SVProgressHUD.show()
        let dataTask = session.dataTask(with: request, completionHandler: { (data, response, error) -> Void in

            if (error != nil) {
                SVProgressHUD.show(withStatus: "Ocorreu um problema")
            } else {
                if let data = data {
                    self.convertoToMovies(data: data)
                }
            }
        })
        
        dataTask.resume()
        
    }

}
