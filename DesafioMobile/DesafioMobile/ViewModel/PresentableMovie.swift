//
//  PresentableMovie.swift
//  DesafioMobile
//
//  Created by Eric Winston on 8/27/19.
//  Copyright © 2019 Eric Winston. All rights reserved.
//


import UIKit

//MARK: - Protocol with the used parts of the movie in the application
protocol PresentableMovieInterface {
    var id: Int {get}
    var name: String {get}
    var bannerImage: UIImage? {get}
    var backDropImage: UIImage? {get}
    var description: String {get}
    var genres: [Int] {get}
    var date: String {get}
}


//MARK: - Init
class PresentableMovie: PresentableMovieInterface{
    
    
    var apiAccess: APIClientInterface
    
    var id: Int = 0
    var name: String = ""
    var bannerImage: UIImage?
    var backDropImage: UIImage?
    var description: String = ""
    var genres: [Int] = []
    var date: String = ""
    
    init(movie: Movie, apiAccess: APIClientInterface, completion: @escaping () -> Void) {
        
        self.id = movie.id
        self.name = movie.title
        self.description = movie.overview
        self.genres = movie.genre_ids
        self.apiAccess = apiAccess
        self.date = fixDate(movieDate: movie.release_date)
        
        if let path = movie.poster_path {
            loadImage(path: path) { [weak self] (image) in
                self?.bannerImage = image
                completion()
            }
        }else{
            bannerImage = UIImage()
        }
        
        if let path = movie.backdrop_path {
            loadImage(path: path) { [weak self] (image) in
                self?.backDropImage = image
            }
        }else{
            backDropImage = UIImage()
        }
    }
    
    convenience init(movie: Movie,apiAccess: APIClientInterface){
        self.init(movie: movie,apiAccess: apiAccess, completion: {}) 
    }

    //MARK: - Methods
    
    /** Loads the movie banner from the api */
    func loadImage(path: String, completion: @escaping (UIImage) -> Void ){
        apiAccess.downloadImage(path: path) { (fetchedImage) in
            if let image = fetchedImage{
                completion(image)
            }
        }
    }
    
    /** Formate the date in the movie */
    func fixDate(movieDate: String) -> String{
   
        let dateF = DateFormatter()
        dateF.dateFormat = "yyyy-MM-dd"
        
        let newF = DateFormatter()
        newF.dateFormat = "dd/MM/yyyy"
        
        if let date = dateF.date(from: movieDate){
            return newF.string(from: date)
        }else{
            return ""
        }
    }
}

