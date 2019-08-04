import Foundation

class GetMovieDetailResponse: NSObject, NSCoding, Decodable {
    
    let genres : [Genere]
    let runtime : Int?
    let credits : Credits?
    
    enum CodingKeys : String, CodingKey {
        case genres = "genres"
        case runtime = "runtime"
        case credits = "credits"
    }
    
    init(_ genres : [Genere], _ runtime : Int, _ credits : Credits) {
        self.genres = genres
        self.runtime = runtime
        self.credits = credits
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(self.genres, forKey: CodingKeys.genres.rawValue)
        aCoder.encode(self.runtime, forKey: CodingKeys.runtime.rawValue)
        aCoder.encode(self.credits, forKey: CodingKeys.credits.rawValue)
    }
    
    required convenience init?(coder aDecoder: NSCoder) {
        let genres = aDecoder.decodeObject(forKey: CodingKeys.genres.rawValue) as! [Genere]
        let runtime = aDecoder.decodeInteger(forKey: CodingKeys.runtime.rawValue)
        let credits = aDecoder.decodeObject(forKey: CodingKeys.credits.rawValue) as! Credits
        self.init(genres, runtime, credits)
    }
    
}
