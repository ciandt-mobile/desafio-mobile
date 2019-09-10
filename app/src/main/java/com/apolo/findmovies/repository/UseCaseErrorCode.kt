package com.apolo.findmovies.repository

enum class UseCaseErrorCode(val messageError : String) {
    NO_INTERNET_CONNECTION("Sem conexão com a internet."),
    UNKNOWN_ERROR("Houve algum problema, tente mais tarde.")
}