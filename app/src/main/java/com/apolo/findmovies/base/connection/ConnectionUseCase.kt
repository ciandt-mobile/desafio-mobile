package com.apolo.findmovies.base.connection

import com.apolo.findmovies.base.AppAplication
import com.apolo.findmovies.base.util.NetworkUtil
import com.apolo.findmovies.repository.UseCaseErrorCode
import com.apolo.findmovies.repository.UseCaseException

class ConnectionUseCase {

    companion object {
        fun testInternetConnection() {
            AppAplication.context?.let {cont ->
                if (NetworkUtil.isInternetAvailable(cont)) {

                } else {
                    throw UseCaseException(userCaseErrorCode = UseCaseErrorCode.NO_INTERNET_CONNECTION)
                }
            }

        }
    }

}