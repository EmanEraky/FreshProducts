package com.example.freshproducts.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.freshproducts.R
import com.example.freshproducts.databinding.ItemFreshBinding
import com.example.freshproducts.domain.model.Fresh
import com.example.freshproducts.presentation.listeners.ListenerFresh

class HomeFreshAdapter(
    private var freshProducts: List<Fresh>,private val listener : ListenerFresh
) :
    RecyclerView.Adapter<HomeFreshAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = DataBindingUtil.inflate<ItemFreshBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_fresh,
            parent,
            false
        )
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = freshProducts.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.binding.listenerFresh =listener
        if(freshProducts[position].favorite){
            holder.binding.imgHeart.setImageResource(R.drawable.heart)
        }else{
            holder.binding.imgHeart.setImageResource(R.drawable.unheart)
        }
        holder.binding.imgHeart.setOnClickListener {
            listener.onClickHeart(freshProducts[position])
            if(freshProducts[position].favorite){
                holder.binding.imgHeart.setImageResource(R.drawable.unheart)
            }else{
                holder.binding.imgHeart.setImageResource(R.drawable.heart)
            }
        }
        holder.binding.freshApi = freshProducts[position]
    }

    class DataViewHolder(val binding: ItemFreshBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateData(list: List<Fresh>) {
        freshProducts=(list)
        notifyDataSetChanged()
    }

}