package com.apolo.findmovies.repository

enum class UseCaseErrorCode(val messageError : String, val errorCode: Int) {
    NO_INTERNET_CONNECTION("Sem conexão com a internet.",300),
    UNKNOWN_ERROR("Houve algum problema, tente mais tarde.", 999)
}