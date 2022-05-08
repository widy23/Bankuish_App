package com.example.bankuishapp.data

import android.provider.Telephony.TextBasedSmsColumns.BODY
import com.example.bankuishapp.representation.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level


object RetrofitInstance {

    private val retrofitCall by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

    }
    val myApi :ApiServiceInstance by lazy {
        retrofitCall.create(ApiServiceInstance::class.java)
    }
}