package com.example.bankuishapp.representation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankuishapp.domain.repository.Repository
import com.example.bankuishapp.representation.model.ResponseApi
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) :ViewModel() {

    private var _myResponse = MutableLiveData<Response<ResponseApi>>()
    val myResponse : LiveData <Response<ResponseApi>> =_myResponse

    private var progressBar = MutableLiveData<Boolean>()
    val pBar: LiveData<Boolean> = progressBar

    private val page = mutableSetOf(1)
    private var scrollPosition =0

    fun getGitHubRepo(language :String,per_page :Int,page :Int){
        viewModelScope.launch {
          progressBar.postValue(true)
            val responseData = repository.getListToRepository(language,per_page,page)
            if (responseData.isSuccessful){
                _myResponse.value = responseData
                Log.e("Result API", responseData.body().toString())
                progressBar.postValue(false)
            }
            else{
                Log.e("ERROR API", "ERROR")
                progressBar.postValue(false)                             }
        }
    }
     fun setIsLoading(isLoading:Boolean){
        progressBar.postValue(isLoading)

    }



}