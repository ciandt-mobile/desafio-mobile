
import UIKit
import Moya

extension Data {
    var prettyPrintedJSONString: String? {
        guard let object = try? JSONSerialization.jsonObject(with: self, options: []),
            let data = try? JSONSerialization.data(withJSONObject: object, options: [.prettyPrinted]),
            let prettyPrintedString = String(data: data, encoding: String.Encoding.utf8) else { return nil }
        
        return prettyPrintedString
    }
}

class AppProvider {
    let provider = MoyaProvider<Connection>();
    let bundle = Bundle.main.bundleIdentifier ?? "com.appCompany.AppName"
    
    public func makeRequest<T:Codable>(_ requestType:Connection, returnClass: T.Type, successCompletion: @escaping(_ result: T) -> Void, failCompletion: @escaping(_ error: Error) -> Void) {
        provider.request(requestType) { (result) in
            switch result {
            case let .success(response):
                do {
                    let decoder = JSONDecoder()
                    print(response.data.prettyPrintedJSONString!)
                    if (response.statusCode >= 200 && response.statusCode <= 299) {
                        let decodedObj = try decoder.decode(returnClass, from: response.data)
                        successCompletion(decodedObj)
                        return
                    } else {
                        let errorResponse = try decoder.decode(ErrorResponseModel.self, from: response.data)
                        failCompletion(errorResponse.getError())
                        return
                    }
                } catch {
                    failCompletion(error)
                    return
                }
            case let .failure(error):
                failCompletion(error)
                return
            }
        }
    }
}

extension AppProvider {
    public func createError(_ bundle:String = "com.appCompany.AppName", _ code:Int = 7779, _ message:String = "Erro de formatação no erro.") -> Error {
        return NSError(domain: bundle, code:7779, userInfo:[ NSLocalizedDescriptionKey: message])
    }
}
