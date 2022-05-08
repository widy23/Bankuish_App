package com.example.bankuishapp.representation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.bankuishapp.R
import com.example.bankuishapp.domain.repository.Repository
import com.example.bankuishapp.representation.utils.MyProgressDialog
import com.example.bankuishapp.representation.viewModel.MainViewModel
import com.example.bankuishapp.representation.viewModel.MainViewModelFactory


class MainActivity : AppCompatActivity() {
        private lateinit var viewModel: MainViewModel

        private lateinit var progressDialog: MyProgressDialog
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                val repository = Repository()
                val mainViewModelFactory = MainViewModelFactory(repository)
                viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
                progressDialog = MyProgressDialog(this)

                val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_navigation) as NavHostFragment
                val navController = navHostFragment.navController

               // viewModel.setIsLoading(true)
                loading()

        }

        private fun loading() {



                }
        }



