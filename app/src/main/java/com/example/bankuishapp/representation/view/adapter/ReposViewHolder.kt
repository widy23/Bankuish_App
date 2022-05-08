package com.example.bankuishapp.representation.view.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bankuishapp.databinding.ItemsReposBinding
import com.example.bankuishapp.representation.model.ItemsRepoListModel
import com.example.bankuishapp.representation.utils.Constants.Companion.DATA_LANGUAGE
import com.example.bankuishapp.representation.utils.Constants.Companion.ID
import com.example.bankuishapp.representation.utils.Constants.Companion.NAME
import com.example.bankuishapp.representation.utils.Constants.Companion.REPOS_ID
import com.example.bankuishapp.representation.utils.Navigation

class ReposViewHolder(view : View) :RecyclerView.ViewHolder(view)  {
    private val binding = ItemsReposBinding.bind(view)
    @SuppressLint("SetTextI18n")
    fun bind(items: ItemsRepoListModel,listener:Navigation , position :Int) {
        binding.txtLanguage.text = DATA_LANGUAGE + items.language
        binding.txtName.text =NAME + items.name
        binding.txtReposId.text= REPOS_ID + items.id
        binding.txtIdOwner.text =ID + items.owner?.id
        binding.txtOwnerName.text= NAME + items.owner?.login

        binding.intentClick.setOnClickListener {
            listener.navigateToDetails(position)
        }
    }
}