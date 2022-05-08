package com.example.bankuishapp.representation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseApi (
    @SerializedName("items")
    @Expose
    val items :List<ItemsRepoListModel>
        )