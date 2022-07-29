package com.example.fruits.presention.adapter

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fruits.R
import com.example.fruits.data.model.Result
import com.example.fruits.databinding.RecycleFruitsItemBinding

class FruitsHomeAdapter : RecyclerView.Adapter<FruitsHomeAdapter.FruitsHomeAdapterHolder>() {
    lateinit var context: Context
    private val callBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
    var differ = AsyncListDiffer(this, callBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitsHomeAdapterHolder {
        context = parent.context
        return FruitsHomeAdapterHolder(
            RecycleFruitsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FruitsHomeAdapterHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class FruitsHomeAdapterHolder(private val binding: RecycleFruitsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(unsplashImage: Result) {
            binding.apply {
                Glide.with(imageItem.context)
                    .load(unsplashImage.urls.regular)
                    .into(imageItem)
                for (x in unsplashImage.tags) {
                    textTitle.text = x.title
                }


                var f = false
                floatingActionButton.setOnClickListener {
                    floatingActionButton.setImageResource(R.drawable.select_favo)

                    if (f) {
                        f = false
                        floatingActionButton.setImageResource(R.drawable.select_favo)
                    } else {
                        f = true
                        floatingActionButton.setImageResource(R.drawable.unselect_favo)
                    }

                }
            }
        }
    }


}