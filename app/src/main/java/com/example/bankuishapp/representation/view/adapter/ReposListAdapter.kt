package com.example.bankuishapp.representation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bankuishapp.R
import com.example.bankuishapp.representation.model.ItemsRepoListModel
import com.example.bankuishapp.representation.utils.Navigation
import com.example.bankuishapp.representation.view.fragments.ReposListFragment

class ReposListAdapter(private val repos:List<ItemsRepoListModel>, private val listener: ReposListFragment) : RecyclerView.Adapter<ReposViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.items_repos,parent,false)
        return ReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
    val items =repos[position]
    holder.bind(items, listener,position)
         }

    override fun getItemCount(): Int {
       return repos.size
    }
}

