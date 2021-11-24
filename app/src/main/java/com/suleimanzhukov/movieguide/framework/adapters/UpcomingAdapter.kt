package com.suleimanzhukov.movieguide.framework.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suleimanzhukov.movieguide.databinding.RecycleViewUpcomingItemBinding
import com.suleimanzhukov.movieguide.model.entities.Movie

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.MainViewHolder>() {
    private lateinit var binding: RecycleViewUpcomingItemBinding
    private var moviesData: List<Movie> = listOf()

    @SuppressLint("notifyDataSetChanged")
    fun setUpcomingMovies(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = RecycleViewUpcomingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }
}