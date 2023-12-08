package com.example.fragment_sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.fragment_sample.databinding.FragmentSecondBinding
import com.example.fragment_sample.model.Product

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(FirstFragment.REQUEST_PRODUCT_DETAIL, this) { _, bundle ->
            val selectedProduct: Product = bundle.getParcelable("SELECTED_PRODUCT")!!
            binding.tvName.text = selectedProduct.pname
            binding.tvDetail.text = selectedProduct.detail
            binding.tvPrice.text = "¥ ${selectedProduct.price}"  // 가격 표시

            Glide.with(this)
                .load(selectedProduct.image_url)
                .into(binding.ivProductImage)
        }
    }
}
