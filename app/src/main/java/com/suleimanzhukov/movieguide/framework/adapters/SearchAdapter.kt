package com.suleimanzhukov.movieguide.framework.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.suleimanzhukov.movieguide.databinding.RecycleViewSearchItemBinding
import com.suleimanzhukov.movieguide.databinding.RecycleViewUpcomingItemBinding
import com.suleimanzhukov.movieguide.framework.OnItemClickListener
import com.suleimanzhukov.movieguide.model.entities.Movie

class SearchAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private lateinit var binding: RecycleViewSearchItemBinding
    private var moviesData: List<Movie> = listOf()

    @SuppressLint("notifyDataSetChanged")
    fun setSearchResultMovies(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie, position: Int) = with(binding) {
            titleUpcoming.text = movie.title
            imageViewUpcoming.load(movie.poster)
            root.setOnClickListener { onItemClickListener.onMovieClickListener(position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = RecycleViewSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(moviesData[position], position)
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }
}