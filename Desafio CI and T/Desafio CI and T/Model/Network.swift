//
//  Network.swift
//  Desafio CI&T
//
//  Created by Mateus Kamoei on 31/07/19.
//  Copyright © 2019 Mateus Kamoei. All rights reserved.
//

import Foundation

protocol NetworkDelegate {
    func didFinishGettingPopularMovies(_ dictionary: [String: Any])
    func didFailGettingPopularMovies(_ error: Error?)
    func didFinishGettingMovieDetails(_ dictionary: [String: Any])
    func didFailGettingMovieDetails(_ error: Error?)
    func didFinishGettingMovieCredits(_ dictionary: [String: Any])
    func didFailGettingMovieCredits(_ error: Error?)
}

// Forcing protocol methods to be optional
extension NetworkDelegate {
    func didFinishGettingPopularMovies(_ dictionary: [String: Any]) {}
    func didFailGettingPopularMovies(_ error: Error?) {}
    func didFinishGettingMovieDetails(_ dictionary: [String: Any]) {}
    func didFailGettingMovieDetails(_ error: Error?) {}
    func didFinishGettingMovieCredits(_ dictionary: [String: Any]) {}
    func didFailGettingMovieCredits(_ error: Error?) {}
}

class Network {
    
    let kAPIKey = "b2458d509e2728114cff394647cc7ff9"
    let kBaseURL = "https://api.themoviedb.org/3/movie/"
    
    let delegate: NetworkDelegate
    
    init(delegate: NetworkDelegate) {
        self.delegate = delegate
    }
    
    func getPopularMovies() {
        let urlString = "\(kBaseURL)popular?api_key=\(kAPIKey)"
        guard let url = URL(string: urlString) else { return }
        let request = URLRequest(url: url, cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 10)
        let dataTask = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if error == nil {
                do {
                    guard let data = data else { return }
                    guard let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any] else { return }
                    self.delegate.didFinishGettingPopularMovies(json)
                } catch {
                    self.delegate.didFailGettingPopularMovies(nil)
                }
            } else {
                self.delegate.didFailGettingPopularMovies(error!)
            }
        }
        dataTask.resume()
    }
    
    func getMovieDetails(_ movieId: Int) {
        let urlString = "\(kBaseURL)\(movieId)?api_key=\(kAPIKey)"
        guard let url = URL(string: urlString) else { return }
        let request = URLRequest(url: url, cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 10)
        let dataTask = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if error == nil {
                do {
                    guard let data = data else { return }
                    guard let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any] else { return }
                    self.delegate.didFinishGettingMovieDetails(json)
                } catch {
                    self.delegate.didFailGettingMovieDetails(nil)
                }
            } else {
                self.delegate.didFailGettingMovieDetails(error!)
            }
        }
        dataTask.resume()
    }
    
    func getMovieCredits(_ movieId: Int) {
        let urlString = "\(kBaseURL)\(movieId)/credits?api_key=\(kAPIKey)"
        guard let url = URL(string: urlString) else { return }
        let request = URLRequest(url: url, cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 10)
        let dataTask = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if error == nil {
                do {
                    guard let data = data else { return }
                    guard let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any] else { return }
                    self.delegate.didFinishGettingMovieCredits(json)
                } catch {
                    self.delegate.didFailGettingMovieCredits(nil)
                }
            } else {
                self.delegate.didFailGettingMovieCredits(error!)
            }
        }
        dataTask.resume()
    }
}

