//
//  MovieAPI.swift
//  Movs
//
//  Created by Eduardo Pereira on 28/07/19.
//  Copyright © 2019 Eduardo Pereira. All rights reserved.
//

import UIKit

fileprivate let apiKey = "api_key=4d0fcba3ff303036e7acc80cc54f5f24"
fileprivate let baseUrl = "https://api.themoviedb.org/3"
fileprivate let imageURL = "http://image.tmdb.org/t/p/w@/"
fileprivate let genresUrl = "https://api.themoviedb.org/3/genre/movie/list?\(apiKey)"
fileprivate let creditUrl = "\(baseUrl)/movie/@/credits?\(apiKey)"



/**
 Possible requests from API
 */
enum Request{
    case upcoming
    case popular
    case find(Int)
    func toString()->String{
        switch self {
        case .upcoming:
            return "\(baseUrl)/movie/upcoming?\(apiKey)&language=en-US"
        case .popular:
            return "\(baseUrl)/movie/popular?\(apiKey)&language=en-US"
        case .find(let id):
            return  "\(baseUrl)/movie/\(id)?\(apiKey)&append_to_response=videos"
        }
    }
}

final class MovieAPI {

    /**
     Build and validate URL construction
     
     - Parameter path: Path for image gotten from the movie object.
     
     - Throws: `NetworkError.invalidURL(String)`
     */
    func BuildURL(path:String)throws->URL{
        guard let url = URL(string:path)else{
            throw NetworkError.invalidURL("Cannot build URL with given path")
        }
        return url
    }
    

    
    /**
     Generic function for requests
     */
     func request(path:String,_ code:@escaping (Data?,Error?) ->Void){
        do {
            let url = try BuildURL(path: path)
            URLSession.shared.dataTask(with: url) { (data, response, err) in
                //check for data
                guard let data = data else{return}
                code(data,nil)
                }.resume()
        } catch let err {
            code(nil,err)
            print(err)
            return
        }
        
        
    }

    /**
     Get poster image from the API
     
     - Parameter path: Path for image gotten from the movie object.
     
     */
    func getPosterImage(width:Int,path:String,onComplete:@escaping(UIImage?)->Void){
        var urlPath = imageURL.replacingOccurrences(of: "@", with: "\(width)")
        urlPath.append(path)
        
        request(path: urlPath) { (data,err) in
            guard let data = data else{
                return
            }
            let image = UIImage(data: data)
            //UI updates need to be done on main queue
            DispatchQueue.main.async {
                onComplete(image ?? UIImage(named: "image_not_found"))
            }
        }
        
        
        }

    /**
     Custom request for movies
     */
    func movieRequest(mode:Request,page:Int,onComplete:@escaping ([Movie],Error?)->Void){
        var path = mode.toString()
        path.append("&page=\(page)")
        request(path: path) { (data,err) in
            
            guard let data = data else{
                onComplete([], NetworkError.invalidURL("Cold not retrieve data"))
                return
             
            }
            let decoder = JSONDecoder()
            do{
                let movieRequest = try decoder.decode(APIRequest<Movie>.self, from: data)
                onComplete(movieRequest.results, nil)
            }catch let err{
                onComplete([], err)
            }
        }
    }
}
extension MovieAPI:DataAcess{

    func getCast(id: String, _ fetch: @escaping ([Cast]) -> ()) {
        let path = creditUrl.replacingOccurrences(of: "@", with: "\(id)")
        request(path: path) { (data, error) in
            guard let data = data else{
                return
            }
            let decoder = JSONDecoder()
            do{
                let castRequest = try decoder.decode(CastRequest.self, from: data)
                fetch(castRequest.cast)
            }catch _{
                fetch([])
            }
        }
    }
    func getDetail(id: Int, _ fetch: @escaping (Movie?) -> ()){
        self.request(path: Request.find(id).toString()) { (data, error) in
            guard let data = data else{
                return
            }
            let decoder = JSONDecoder()
            do{
                let movieRequest = try decoder.decode(Movie.self, from: data)
                fetch(movieRequest)
            }catch _{
                fetch(nil)
            }
            
        }
    }
    func getImage(path: String, width: Int, _ fetch: @escaping (UIImage?) -> ()) {
        getPosterImage(width: width, path: path) { (image) in
            fetch(image)
        }
    }
    
    func getMovies(request:Request,page:Int,_ fetch: @escaping ([Movie]) -> ()) {
        movieRequest(mode: request, page: page) { (result, error) in
            if error != nil{
                return
            }
            fetch(result)
        }
    }
}


