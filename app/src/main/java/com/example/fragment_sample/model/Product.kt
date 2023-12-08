package com.example.fragment_sample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val product_no: String,
    val pname: String,
    val category: String,
    val price: Int,
    val image_url: String,
    val detail: String
) : Parcelable