package com.example.fragment_sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragment_sample.databinding.ProfileItemBinding
import com.example.fragment_sample.model.Product

class ProductAdapter(
    private var productList: List<Product>,
    private val onProductClicked: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProfileViewHolder>() {

    inner class ProfileViewHolder(private val binding: ProfileItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.tvName.text = product.pname
            binding.tvCategory.text = product.category
            binding.tvPrice.text = "¥ ${product.price}" // Assuming you want to display the price as well

            Glide.with(binding.root.context)
                .load(product.image_url)
                .into(binding.ivProduct2)

            // クリックリスナーを実装
            binding.root.setOnClickListener {
                // 実行する処理
                onProductClicked(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val product: Product = productList[position]
        holder.bind(product)
    }

    fun updateData(newData: List<Product>) {
        productList = newData
        notifyDataSetChanged()
    }
}
