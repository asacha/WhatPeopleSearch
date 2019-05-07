package com.ascstb.whatpeoplesearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String,
    var score: Int = 0
) : Parcelable