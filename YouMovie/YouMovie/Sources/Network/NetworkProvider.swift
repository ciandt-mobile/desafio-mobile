//
//  NetworkProvider.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Alamofire

// MARK: - Definitions

typealias JSON = [String: Any]
typealias RequestEmptySuccess = () -> Void
typealias RequestSuccess = (_ result: JSON) -> Void
typealias RequestFailure = (_ error: RequestError) -> Void

// MARK: - NetworkProviderProtocol

protocol NetworkProviderProtocol {

    func request(urlString: String,
                 method: HTTPMethod,
                 parameters: Parameters?,
                 encoding: ParameterEncoding,
                 headers: HTTPHeaders?,
                 success: @escaping RequestSuccess,
                 failure: @escaping RequestFailure)
}

extension NetworkProviderProtocol {

    func request(urlString: String,
                 method: HTTPMethod,
                 parameters: Parameters? = nil,
                 encoding: ParameterEncoding = JSONEncoding.default,
                 headers: HTTPHeaders? = nil,
                 success: @escaping RequestSuccess,
                 failure: @escaping RequestFailure) {

        return self.request(urlString: urlString,
                            method: method,
                            parameters: parameters,
                            encoding: encoding,
                            headers: headers,
                            success: success,
                            failure: failure)
    }
}

// MARK: - NetworkProvider

struct NetworkProvider: NetworkProviderProtocol {

    // MARK: - Private Properties

    private let isDebuggingJSON: Bool = false

    // MARK: - Internal Methods

    func request(urlString: String,
                 method: HTTPMethod,
                 parameters: Parameters?,
                 encoding: ParameterEncoding,
                 headers: HTTPHeaders?,
                 success: @escaping RequestSuccess,
                 failure: @escaping RequestFailure) {

        DispatchQueue.global(qos: .background).async {

            let request = AF.request(urlString,
                                     method: method,
                                     parameters: parameters,
                                     encoding: encoding,
                                     headers: headers).validate()

            request.responseJSON(completionHandler: { dataResponse in

                print("📦 REQUEST: \(request.cURLDescription())")

                switch dataResponse.result {
                case .success(let result):

                    let json: JSON = result as? JSON ?? [:]

                    if self.isDebuggingJSON {
                        print("📦 JSON: \(json)")
                    }

                    print("✅ REQUEST SUCCEEDED")
                    success(json)
                case .failure(let error):
                    print("❌ REQUEST FAILED")
                    failure(RequestError(error))
                }
            })
        }
    }
}
