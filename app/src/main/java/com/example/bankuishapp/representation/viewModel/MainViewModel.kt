package com.example.bankuishapp.representation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankuishapp.domain.repository.Repository
import com.example.bankuishapp.representation.model.ErrorResponse
import com.example.bankuishapp.representation.model.ItemsRepoListModel
import com.example.bankuishapp.representation.model.ResponseApi
import com.example.bankuishapp.representation.utils.ApiResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class MainViewModel(private val repository: Repository) :ViewModel() {

    private var _myResponse = MutableLiveData<List<ItemsRepoListModel>>()
    val myResponse : LiveData <List<ItemsRepoListModel>> =_myResponse

    private var progressBar = MutableLiveData<Boolean>()

    private var _errorMessage = MutableLiveData <ErrorResponse>()
    val errorMessage: LiveData<ErrorResponse> = _errorMessage


    @OptIn(DelicateCoroutinesApi::class)
    suspend fun getGitHubRepo(language :String, per_page :Int, page :Int){

          progressBar.postValue(true)
            GlobalScope.launch(Dispatchers.IO) {
               ApiResponse.createCall({repository.getListToRepository(language,per_page,page)},{
                  val info =it.items
                      _myResponse.postValue(info)
                   Log.e("ResponsData",info.toString())
               },{
                   Log.e("ResponseData",it.message)
                   _errorMessage.postValue(it)

               })


            }



        //            if (responseData.data.items.isNullOrEmpty()){
//                _myResponse.value = responseData
//                progressBar.postValue(false)
//            }
//            else{
//                progressBar.postValue(false)
//
//
//            }
        }
    }










