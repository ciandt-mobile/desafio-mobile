package com.apolo.findmovies.repository

import java.lang.RuntimeException

class UseCaseException(private val errorMessage : String? = null, val userCaseErrorCode: UseCaseErrorCode) : RuntimeException(errorMessage)