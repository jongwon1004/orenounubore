package com.example.fragment_sample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    val name: String,
    val category: String,
    val detail:String,
    val imgResID: Int,
    val imgResID2: Int
) : Parcelable



