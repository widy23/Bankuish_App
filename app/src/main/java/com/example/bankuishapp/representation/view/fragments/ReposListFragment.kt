package com.example.bankuishapp.representation.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankuishapp.R
import com.example.bankuishapp.databinding.FragmentReposListBinding
import com.example.bankuishapp.domain.repository.Repository
import com.example.bankuishapp.representation.model.ItemsRepoListModel
import com.example.bankuishapp.representation.utils.Constants
import com.example.bankuishapp.representation.utils.MyProgressDialog
import com.example.bankuishapp.representation.utils.Navigation
import com.example.bankuishapp.representation.view.adapter.ReposListAdapter
import com.example.bankuishapp.representation.viewModel.MainViewModel
import com.example.bankuishapp.representation.viewModel.MainViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ReposListFragment : Fragment(),Navigation {
    private lateinit var viewModel: MainViewModel
    private val repository = Repository()
    private lateinit var  binding : FragmentReposListBinding
    private lateinit var adapter: ReposListAdapter
    private val mutableListRepos = mutableListOf<ItemsRepoListModel>()
    private lateinit var progressDialog: MyProgressDialog
    var id: Int?=null
    var name: String?=null
    var fullName: String?=null

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,mainViewModelFactory)[MainViewModel::class.java]
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.getGitHubRepo(Constants.LANGUAGE, Constants.PER_PAGE, Constants.PAGE)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentReposListBinding.inflate(layoutInflater)
        val view = binding.root
        progressDialog= MyProgressDialog(activity)
        retrieveData()
        setUpRecycleViewData()

        binding.swipe.setOnRefreshListener {
            setUpRecycleViewData()
        }

        return view
    }
    private fun setUpRecycleViewData() {
        adapter = ReposListAdapter(mutableListRepos ,this)
        binding.myRecycleLList.layoutManager = LinearLayoutManager(context)
        binding.myRecycleLList.adapter = adapter
         binding.swipe.isRefreshing=false

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun retrieveData() {
    progressDialog.startProgressDialog()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { result ->
            if (result.isNotEmpty () ){
                mutableListRepos.clear()
                Log.d("ERROR",result.toString())
                val newList = result as ArrayList<ItemsRepoListModel>
                val filter: List<ItemsRepoListModel> = newList.filter { s -> s.language == "Kotlin" }
                Log.d("FILTER",filter.toString())
                filter.let {mutableListRepos.addAll(it) }
                adapter.notifyDataSetChanged()
               progressDialog.stopProgressDialog()

            }else {
                showError("result.error!!.message")
                progressDialog.stopProgressDialog()
            }
//
//                viewModel.errorMessage.observe(viewLifecycleOwner){result->
//
//                 showError(result.error!!.message)
//
//                    Log.d("ERROR_API_CALL",result.error.message)
//                    progressDialog.stopProgressDialog()
//                }
//            }
//            Log.d("ERROR_API_CALL",result.toString())
            progressDialog.stopProgressDialog()
        })
    }

    private fun showError(error: String) {
        Log.d("ERROR_API_CALL","result.error.message")
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage(error)
            ?.setTitle(R.string.retry)
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }

    @SuppressLint("ResourceType")
   override fun navigateToDetails(position: Int) {
       val fullName : String? =mutableListRepos[position].full_name
       val language : String? =mutableListRepos[position].name
       val id :Int =mutableListRepos[position].id
       val rest= ItemsRepoListModel(id,language,fullName,"",null)
        Log.d("Selected",mutableListRepos[position].toString() )
        val action = ReposListFragmentDirections.actionReposListFragmentToDetailsFragment(rest)
       findNavController().navigate(action)
        }

}