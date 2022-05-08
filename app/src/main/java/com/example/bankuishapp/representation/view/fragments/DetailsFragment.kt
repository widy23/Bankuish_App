package com.example.bankuishapp.representation.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.bankuishapp.databinding.FragmentDetailsBinding
import com.example.bankuishapp.representation.model.ItemsRepoListModel
import com.example.bankuishapp.representation.viewModel.MainViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: MainViewModel

    private val itemSelect = mutableListOf<ItemsRepoListModel>()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        val view = binding.root
       var data = args.data
        binding.txtId.text= " Id :${args.data.id}"
        binding.txtFullName.text= "Full Name :${ args.data.full_name}"
        binding.txtName.text= "Name  : ${args.data.language}"
        return view
    }
            }





