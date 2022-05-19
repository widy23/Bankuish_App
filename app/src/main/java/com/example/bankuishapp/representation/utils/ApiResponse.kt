package com.example.bankuishapp.representation.utils

import android.util.Log
import com.example.bankuishapp.representation.model.ErrorResponse
import com.example.bankuishapp.representation.model.ItemsRepoListModel
import okhttp3.ResponseBody
import retrofit2.Response


data class ApiResponse<out T>(val status:Status,val data :T, val error : ErrorResponse?){

enum class Status{
    SUCCESS,
    ERROR,
    LOADING;

}
        companion object {
            private fun <T> success(data: T): ApiResponse<T> {
                return ApiResponse(Status.SUCCESS, data,null )
            }
            fun <T> successEmpty(): ApiResponse<Nothing?> {
                return ApiResponse(Status.SUCCESS, null, null)
            }

            fun <T> error(data: T? = null, error:ErrorResponse? = null): ApiResponse<T?> {
                return ApiResponse(Status.ERROR, data, error)
            }

            fun <T> loading(data: T? = null): ApiResponse<T?> {
                return ApiResponse(Status.LOADING, data, null)
            }

            suspend fun <T : Any> createCall(
                call: suspend () -> Response<T>, onSuccess: (T) -> Unit,
                onError: (ErrorResponse) -> Unit) {
                try {
                    val response = call()

                    if (response.isSuccessful) {
                        onSuccess(response.body()!!)
                    } else {
                    val myError = ErrorResponse(response.errorBody().toString())
                        onError(myError!!)

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                  //  onError(ResponseBody)
                }
            }



        }




}