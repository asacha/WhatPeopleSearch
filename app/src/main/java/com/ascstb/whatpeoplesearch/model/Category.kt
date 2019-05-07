package com.ascstb.whatpeoplesearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    var description: String
) : Parcelable