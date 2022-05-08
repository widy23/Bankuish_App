package com.example.bankuishapp.representation.model

import android.os.Parcel
import android.os.Parcelable


data class ItemsRepoListModel(

    val id: Int,
    val language: String?,
    val full_name: String?,
    val name: String?,
    val owner: Owner?

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Owner::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(language)
        parcel.writeString(full_name)
        parcel.writeString(name)
        parcel.writeParcelable(owner, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemsRepoListModel> {
        override fun createFromParcel(parcel: Parcel): ItemsRepoListModel {
            return ItemsRepoListModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemsRepoListModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class Owner(val login: String?, val id:Int, val node_id: String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeInt(id)
        parcel.writeString(node_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Owner> {
        override fun createFromParcel(parcel: Parcel): Owner {
            return Owner(parcel)
        }

        override fun newArray(size: Int): Array<Owner?> {
            return arrayOfNulls(size)
        }
    }
}
