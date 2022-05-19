package com.example.bankuishapp.representation.model

import com.google.gson.annotations.SerializedName


data class ErrorResponse(
    @SerializedName("message")
    val message:String)
