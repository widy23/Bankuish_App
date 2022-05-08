package com.example.bankuishapp.representation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsModel(val id:Int, val name: String, val fullName:String):Parcelable