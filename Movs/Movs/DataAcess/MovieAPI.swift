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
fileprivate let trailerURL = "http://api.themoviedb.org/3/movie/@/videos?\(apiKey)"
fileprivate let youtubeUrl  = "https://www.youtube.com/watch?v="
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
            return  "\(baseUrl)/movie/\(id)?\(apiKey)&language=en-US"
        }
    }
}

final class MovieAPI:DataAcess {

    var genres: [Genre] = []
    
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
    func getDuration(id: Int, _ fetch: @escaping (Int?) -> ()) {
        self.request(path: Request.find(id).toString()) { (data, error) in
            guard let data = data else{
                return
            }
            let decoder = JSONDecoder()
            do{
                let movieRequest = try decoder.decode(Movie.self, from: data)
                fetch(movieRequest.runtime)
            }catch _{
                fetch(nil)
            }

        }
    }
    init() {
        getGenres { [weak self](result) in
            self?.genres = result
        }
    }
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
    
    func getGenres(fetch: @escaping ([Genre]) -> Void) {
        request(path:genresUrl ) { (data,err)  in
            guard let data = data else{
                return
            }
            do{
                //validate self
                let result = try JSONDecoder().decode(GenreRequest.self, from: data)
                fetch(result.genres)
            }catch let err{
                print(err)
            }
        }
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
     Call for a trailer on youtube
     - Parameter id: id from the movie
     
     */
    func requestYoutube(id:String){
        //validade self
             getYoutubeUrl(id: id) { path,err  in
                if let error = err,let _ = path{
                    print(error)
                    return
                }
                do{
                    let url = try self.BuildURL(path: path!)
                    DispatchQueue.main.async {
                        if UIApplication.shared.canOpenURL(url) {
                            UIApplication.shared.open(url, options: [:], completionHandler: nil)
                        }
                    }
                }catch let err{
                    //TODO:Criar alerta de video indisponivel
                    print(err)
                    return
                }
                
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
     Get results on request for movies on the api
     - Parameter id: id from the movie
     
    */
    private func getMovieTrailer(id:String,onComplete:@escaping (APIRequest<Video>?,NetworkError?)->Void){
        let adjustedPath = trailerURL.replacingOccurrences(of: "@", with: id)
        request(path: adjustedPath) { (data,err) in
            guard let data = data else{
                return
            }
            do{
                let videos = try JSONDecoder().decode(APIRequest<Video>.self, from: data)
                //API return an empty array for no results :(
                if videos.results.isEmpty{
                    onComplete(nil,NetworkError.invalidVideo("Videos not found"))
                }else{
                      onComplete(videos,nil)
                }
               
            }catch let err{
                print(err)
            }
           
        }
    }
    /**
     Get youtube url for the id given on video requests
     - Parameter id: id from the trailer at youtube
     
     */
    func getYoutubeUrl(id:String,onComplete:@escaping (String?,NetworkError?)->Void){
        getMovieTrailer(id: id) { (request,err) in
            if let firstResult = request?.results.first{
                    var err:NetworkError? = nil
                    var url = ""
                    if let key = firstResult.key{
                         url = "\(youtubeUrl)\(key)"
                    } else{
                        err = NetworkError.invalidVideo("Video not Found")
                    }
                        onComplete(url, err)
                    
                    
            }else{
                 onComplete(nil, err)
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


