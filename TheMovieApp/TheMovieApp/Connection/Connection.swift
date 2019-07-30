
import Moya

enum Connection {
    case getPopularMovies
    case getGenreList
}

extension Connection: TargetType {
    
    var baseURL: URL { return URL(string: "https://api.themoviedb.org/3")! }
    
    var path: String {
        switch self {
        case .getPopularMovies:
            return "/movie/popular"
        case .getGenreList:
            return "/genre/movie/list"
        }
    }
    
    var method: Moya.Method {
        switch self {
        case .getPopularMovies, .getGenreList:
            return .get
        }
    }
    
    var task: Task {
        let params = ["api_key" : "de94a58f8f1c55104fa3b01661f5c0d5"]
        switch self {
        case .getPopularMovies, .getGenreList:
            return .requestParameters(parameters: params, encoding: URLEncoding(destination: .queryString))
        }
    }
    
    var sampleData: Data {
        switch self {
        case .getPopularMovies, .getGenreList:
            return "{\"userToken\":\"123123\"}".utf8Encoded;
        }
    }
    
    var headers: [String : String]? {
        return nil
    }
}

// MARK: - Helpers
private extension String {
    var urlEscaped: String {
        return addingPercentEncoding(withAllowedCharacters: .urlHostAllowed)!
    }
    
    var utf8Encoded: Data {
        return data(using: .utf8)!
    }
}
