package com.example.bankuishapp.domain.repository

import com.example.bankuishapp.data.RetrofitInstance
import com.example.bankuishapp.representation.model.ResponseApi
import retrofit2.Response

class Repository {


   suspend   fun getListToRepository(language :String,per_page :Int,page :Int) : Response<ResponseApi> {
        return RetrofitInstance.myApi.getAllGitRepos(language, per_page,page)
    }


//    suspend fun getDetailsToRepository() : Response<ResponseApi> id_movie,api_key)
//    }
}