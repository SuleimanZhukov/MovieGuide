package com.suleimanzhukov.movieguide.framework.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.databinding.FragmentWishlistBinding
import com.suleimanzhukov.movieguide.framework.OnItemClickListener
import com.suleimanzhukov.movieguide.framework.adapters.WishlistAdapter
import com.suleimanzhukov.movieguide.framework.ui.details.DetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishlistFragment : Fragment() {

    private val wishlistViewModel: WishlistViewModel by viewModel()
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wishlistViewModel.getWishlistLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        wishlistViewModel.getMoviesFromDB()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Loading -> {

            }
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.SuccessWishlist -> {
                val wishlistAdapter = WishlistAdapter(object : OnItemClickListener {
                    override fun onMovieClickListener(position: Int) {
                        val bundle = Bundle().apply {
                            putParcelable(DetailsFragment.DETAILS_KEY, appState.wishlistMovies[position])
                        }

                        activity?.supportFragmentManager!!
                            .beginTransaction()
                            .replace(R.id.container_de_fragmento, DetailsFragment.newInstance(bundle))
                            .addToBackStack("")
                            .commitAllowingStateLoss()
                    }
                })

                wishlistAdapter.setWishlistMovies(appState.wishlistMovies)

                wishlistRecycleView.adapter = wishlistAdapter
                wishlistRecycleView.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = WishlistFragment()
    }
}