import Foundation
import Alamofire

class Network {
    
    static func execRequest(url : String, success : @escaping ( _ result : Any? ) -> (), error : @escaping ( _ error : String) -> ()) {
        
        if (NetworkReachabilityManager()?.isReachable ?? false) {
            Alamofire.request("\(Constants.BASE_URL)\(url)").responseJSON { response in
                if let json = response.result.value {
                    success(json)
                } else {
                    error("Fail to load this request")
                }
            }
        } else {
            error("No connection. Please check your network connection")
        }
    }
    
}
