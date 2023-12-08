package com.example.fragment_sample

import com.example.fragment_sample.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {

    @GET("pizzaDB.php") // 여기에 실제 API 엔드포인트를 사용합니다.
    fun getProducts(): Call<List<Product>>
}
