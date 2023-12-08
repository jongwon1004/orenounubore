package com.example.fragment_sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fragment_sample.adapter.ProductAdapter
import com.example.fragment_sample.databinding.FragmentFirstBinding
import com.example.fragment_sample.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 설정
        productAdapter = ProductAdapter(listOf()) { product ->
            // Product 클릭시 처리
            navigateToProductDetail(product)
        }
        binding.rvProfilelist.adapter = productAdapter
        binding.rvProfilelist.layoutManager = GridLayoutManager(context, 2)

        // API 호출
        fetchProducts()
    }

    private fun fetchProducts() {
        // Retrofit을 이용해 API 호출하고 데이터를 가져옵니다.
        RetrofitClient.instance.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    // 가져온 데이터로 RecyclerView 어댑터 업데이트
                    productAdapter.updateData(response.body() ?: emptyList())
                } else {
                    // 에러 처리
                    showError(response.message())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                showError(t.message ?: "Unknown error")
            }
        })
    }

    private fun navigateToProductDetail(product: Product) {
        // Product 상세정보를 보여주는 프래그먼트로 이동
        parentFragmentManager.setFragmentResult(
            REQUEST_PRODUCT_DETAIL,
            bundleOf("SELECTED_PRODUCT" to product)
        )
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, SecondFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val REQUEST_PRODUCT_DETAIL = "REQUEST_PRODUCT_DETAIL"
    }
}