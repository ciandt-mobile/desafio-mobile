package com.example.appmoviescit.base.mock

class ApiMock(val url: String, val apiService: MockedApiService?) {
    protected var responseHandler: ResponseHandler? = null
    protected var response: String? = null
    private var code = 200
    var error: HttpException? = null
        private set
    private var method: String? = null

    fun getCode(): Int {
        return code
    }

    fun getResponse(request: Request): String {
        if (responseHandler != null) {
            return responseHandler!!.getResponse(request, url)
        }
        return response ?: ""
    }

    fun throwConnectionError(): ApiMock {
        this.code = FORCED_MOCK_EXCEPTION_CODE
        return this
    }

    //the response code this mock should send
    fun code(code: Int): ApiMock {
        this.code = code
        return this
    }

    //the response body this mock should send
    fun body(response: String): ApiMock {
        this.response = response
        return this
    }

    //a handler to mock dynamically with multiple cases
    fun body(handler: ResponseHandler): ApiMock {
        this.responseHandler = handler
        return this
    }

    //set the ApiMock and make it active
    fun apply() {
        apiService?.let {
            var path = url
            if (method != null) {
                path = "$method $path"
            }
            apiService.addMockedApi(path, this)
        } ?: throw RuntimeException("ApiService not mocked!")

    }

    companion object {
        val FORCED_MOCK_EXCEPTION_CODE = 999
    }
}