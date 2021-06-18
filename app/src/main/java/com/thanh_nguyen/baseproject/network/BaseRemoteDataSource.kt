package com.thanh_nguyen.baseproject.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thanh_nguyen.baseproject.common.Constants
import com.thanh_nguyen.baseproject.model.respone.ErrorResponse
import retrofit2.Response
import kotlinx.coroutines.CancellationException
import okhttp3.ResponseBody

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    return Result.success(body)
            }
            return error(
                " ${response.code()} ${response.message()}",
                response.errorBody(),
                errorCode = response.code()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return when (e) {
                is CancellationException ->
                    error(
                        e.message ?: e.toString(),
                        null,
                        errorCode = Constants.Exception.CANCELLATION_EXCEPTION
                    )
                else -> error(e.message ?: e.toString(), null, errorCode = null)
            }
        }
    }

    private fun <T> error(message: String, errorBody: ResponseBody?, errorCode: Int?): Result<T> {
        Log.e("BaseRemoteDataSource", "Network call has failed for a following reason: $message")
        return if (errorBody == null) {
            Result.error(
                "Network call has failed for a following reason: $message",
                errorCode = errorCode,
                error = null
            )
        } else {
            val gson = Gson()
            val type = object : TypeToken<ErrorResponse>() {}.type
            val errorData: ErrorResponse = gson.fromJson(errorBody.charStream(), type)
            Result.error(
                "Network call has failed for a following reason: $message",
                errorCode = errorCode,
                error = errorData
            )
        }
    }


}