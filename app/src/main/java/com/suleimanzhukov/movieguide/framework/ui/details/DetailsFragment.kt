package com.suleimanzhukov.movieguide.framework.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import coil.load
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.databinding.FragmentDetailsBinding
import com.suleimanzhukov.movieguide.framework.MainActivity
import com.suleimanzhukov.movieguide.framework.ui.search.SearchFragment
import com.suleimanzhukov.movieguide.model.entities.Movie
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private val detailsViewModel: DetailsViewModel by viewModel()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: Movie? = arguments?.getParcelable(DETAILS_KEY)

        addToWishlist(movie!!)

        detailsViewModel.getDetailsLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })

        if (movie.wishlist) {
            wishlist_button.load(R.drawable.red_heart)
            detailsViewModel.getMovieById(movie.id)
        } else {
            detailsViewModel.getDetailsData(movie.id)
        }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Loading -> {

            }
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.SuccessOneMovie -> {
                titleDetails.text = appState.movie.title
                genreDetails.text = appState.movie.genre
                overviewDetails.text = appState.movie.overview
                posterDetails.load(appState.movie.poster)
            }
            is AppState.SuccessGetMovieFromWishlist -> {
                titleDetails.text = appState.wishlistMovie.title
                genreDetails.text = appState.wishlistMovie.genre
                overviewDetails.text = appState.wishlistMovie.overview
                posterDetails.load(appState.wishlistMovie.poster)
            }
            else -> {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addToWishlist(movie: Movie) = with(binding) {
        wishlistButton.setOnClickListener {
            if ((activity as MainActivity).checkPermission()) {
                toWishlistFunction(movie)
            } else {
                (activity as MainActivity).requestPermission()
            }
        }
    }

    private fun toWishlistFunction(movie: Movie) = with(binding) {
        movie.wishlist = !movie.wishlist
        if (movie.wishlist) {
            wishlistButton.load(R.drawable.red_heart)
            detailsViewModel.addMovieToWishlist(movie)
        } else {
            wishlistButton.load(R.drawable.empty_heart)
            detailsViewModel.removeMovieFromWishlist(movie)
        }
    }

    companion object {
        const val DETAILS_KEY = "DETAILS_KEY"

        fun newInstance(bundle: Bundle): DetailsFragment {
            var fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}