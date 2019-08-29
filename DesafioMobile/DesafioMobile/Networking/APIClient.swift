//
//  APIClient.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//

import Foundation
import UIKit

fileprivate let api_key: String = "142734ae2d8944d42c389ce088b2e253"

//MARK: - The paths to access the API
enum ApiPaths{
    case image
    case movies(page: Int,type: String)
    case detailMovie(id: Int)
    
    var path: String{
        switch self {
        case .image:
            return "http://image.tmdb.org/t/p/w500"
        case .movies(let page, let type):
            return "https://api.themoviedb.org/3/movie/\(type)?api_key=\(api_key)&language=en-US&page=\(page)"
        case .detailMovie(let id):
            return "https://api.themoviedb.org/3/movie/\(id)?api_key=\(api_key)"
        }
    }
}

protocol APIClientInterface{
    func fetchData<T:Codable>(path: ApiPaths,type: T.Type,completion: @escaping (T,Error?) -> Void)
    func downloadImage(path: String, completion: @escaping (UIImage?) -> Void)
    func getImageData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ())
}

//MARK: - API Acess Methods
class APIClient: APIClientInterface{
    
    // Fetch the popular movies in the API
    func fetchData<T:Codable>(path: ApiPaths,type: T.Type,completion: @escaping (T,Error?) -> Void){
        
        guard let url = URL(string: path.path) else {return}
        let request = URLRequest(url: url, cachePolicy: .useProtocolCachePolicy,timeoutInterval: 10.0)
        
        let session = URLSession.shared
        let dataTask = session.dataTask(with: request,completionHandler: { (data, response, error) -> Void in
            guard let data = data else{return}
            do{
                let result = try JSONDecoder().decode(T.self, from: data)
                completion(result,error)
            }catch{
                print("\(error)")
            }
        })
        dataTask.resume()
    }
    
    //Fetch the image of a specified movie
    func downloadImage(path: String, completion: @escaping (UIImage?) -> Void){
        guard let url = URL(string: ApiPaths.image.path + path) else {return}
        
        getImageData(from: url) { data, response, error in
            guard let data = data, error == nil else { return }
            if let image = UIImage(data: data){
                completion(image)
            }
        }
    }
    
    //Get the image data
    func getImageData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
        URLSession.shared.dataTask(with: url, completionHandler: completion).resume()
    }
}
