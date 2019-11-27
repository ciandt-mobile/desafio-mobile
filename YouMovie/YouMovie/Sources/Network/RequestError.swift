//
//  RequestError.swift
//  YouMovie
//
//  Created by Michael Douglas on 26/11/19.
//  Copyright © 2019 Michael Douglas. All rights reserved.
//

import Foundation

enum RequestError {

    // MARK: - Definitions

    enum LocalizedError: String {
        case networkError
        case internalError

        var description: String {
            switch self {
            case .networkError:
                return MessagesUtil.Error.networkError
            case .internalError:
                return MessagesUtil.Error.internalError
            }
        }
    }

    case error(RequestError.LocalizedError)

    // MARK: - Internal Properties

    var localizedDescription: String {
        switch self {
        case .error(let error):
            return error.description
        }
    }

    var type: LocalizedError {
        switch self {
        case .error(let errorType):
            return errorType
        }
    }

    // MARK: - Inits

    init(_ error: Error?) {

        guard let errorCode = error?._code,
            Connectivity.isConnectedToInternet else {
                self = .error(.networkError)
                return
        }

        switch errorCode {
        default:
            self = .error(.internalError)
        }
    }
}
