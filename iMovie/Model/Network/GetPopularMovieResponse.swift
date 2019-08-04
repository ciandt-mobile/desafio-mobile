import Foundation

class GetPopularMovieResponse : NSObject, NSCoding, Decodable {
    
    let page : Int
    let totalResults : Int
    let totalPages : Int
    let results : [Movie]
    
    enum CodingKeys : String, CodingKey {
        case page = "page"
        case totalResults = "total_results"
        case totalPages = "total_pages"
        case results = "results"
    }
    
    init(_ page : Int, _ totalResults : Int, _ totalPages : Int, _ results : [Movie]) {
        self.page = page
        self.totalResults = totalResults
        self.totalPages = totalPages
        self.results = results
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(self.page, forKey: CodingKeys.page.rawValue)
        aCoder.encode(self.totalResults, forKey: CodingKeys.totalResults.rawValue)
        aCoder.encode(self.totalPages, forKey: CodingKeys.totalPages.rawValue)
        aCoder.encode(self.results, forKey: CodingKeys.results.rawValue)
    }
    
    required convenience init?(coder aDecoder: NSCoder) {
        let page = aDecoder.decodeInteger(forKey: CodingKeys.page.rawValue)
        let totalResults = aDecoder.decodeInteger(forKey: CodingKeys.totalResults.rawValue)
        let totalPages = aDecoder.decodeInteger(forKey: CodingKeys.totalPages.rawValue)
        let results = aDecoder.decodeObject(forKey: CodingKeys.results.rawValue) as! [Movie]
        self.init(page, totalResults, totalPages, results)
    }
}
