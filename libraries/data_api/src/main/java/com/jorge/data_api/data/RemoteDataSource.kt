package com.jorge.data_api.data

import com.jorge.data_api.model.ApiResult
import retrofit2.Response
import java.io.IOException

open class RemoteDataSource {

    open fun <T : Any> handleApiResponse(response: Response<T>): ApiResult<T> {
        return if (response.isSuccessful) {
            ApiResult.Success(response.body()!!)
        } else {
            ApiResult.Error(IOException(response.message()))
        }
    }
}