import Foundation

class MovieAPI {
    
    static func getMovies(_ page : Int, _ filter : MovieFilter, success : @escaping ( _ result : GetPopularMovieResponse ) -> (), error : @escaping ( _ error : String) -> ()) {
        
        Network.execRequest(url: "movie/\(filter)?page=\(page)&api_key=\(Constants.API_KEY)", success: { (result) in
            do {
                let jsonResponse = try JSONSerialization.data(withJSONObject: result, options: .prettyPrinted)
                let getPopularMovieResponse = try JSONDecoder().decode(GetPopularMovieResponse.self, from: jsonResponse)
                success(getPopularMovieResponse)
            } catch let parsingError {
                error("Ops! Fail to load movie list, please try again")
                print(parsingError)
            }
            
        }) { (errorMessage) in
            error(errorMessage)
        }
    }
    
    static func getMovieDetail(id : Int, success : @escaping ( _ result : GetMovieDetailResponse ) -> (), error : @escaping ( _ error : String) -> ()) {
        
        Network.execRequest(url: "movie/\(id)?append_to_response=credits&api_key=\(Constants.API_KEY)", success: { (result) in
            do {
                let jsonResponse = try JSONSerialization.data(withJSONObject: result, options: .prettyPrinted)
                let getMovieDetailResponse = try JSONDecoder().decode(GetMovieDetailResponse.self, from: jsonResponse)
                success(getMovieDetailResponse)
            } catch let parsingError {
                error("Opa! Fail to load movie detail, please try again.")
                print(parsingError)
            }
            
        }) { (errorMessage) in
            error(errorMessage)
        }
    }
    
}
