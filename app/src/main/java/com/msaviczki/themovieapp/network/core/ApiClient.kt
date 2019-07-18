package com.msaviczki.themovieapp.network.core

import retrofit2.Retrofit

class ApiClient {
    companion object {
        val client: Retrofit by lazy {
            ApiBuilder(ApiConstants.URL).client
        }
    }
}