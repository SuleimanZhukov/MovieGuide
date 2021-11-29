package com.suleimanzhukov.movieguide.framework.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.databinding.FragmentMainBinding
import com.suleimanzhukov.movieguide.framework.OnItemClickListener
import com.suleimanzhukov.movieguide.framework.adapters.NowPlayingAdapter
import com.suleimanzhukov.movieguide.framework.adapters.UpcomingAdapter
import com.suleimanzhukov.movieguide.framework.ui.details.DetailsFragment
import com.suleimanzhukov.movieguide.framework.ui.wishlist.WishlistFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModel()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.openWishlistButton.setOnClickListener {
            activity?.supportFragmentManager!!
                .beginTransaction()
                .replace(R.id.container_de_fragmento, WishlistFragment.newInstance())
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
        mainViewModel.getMainLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        mainViewModel.getMainData()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Loading -> {

            }
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.SuccessNowPlaying -> {
                val nowPlayingAdapter = NowPlayingAdapter(object : OnItemClickListener {
                    override fun onMovieClickListener() {
                        val bundle = Bundle().apply {
                            putParcelable(DetailsFragment.DETAILS_KEY, appState.nowPlayingMovies[2])
                        }

                        activity?.supportFragmentManager!!
                            .beginTransaction()
                            .replace(R.id.container_de_fragmento, DetailsFragment.newInstance(bundle))
                            .addToBackStack("")
                            .commitAllowingStateLoss()
                    }
                })

                nowPlayingAdapter.setNowPlayingMovies(appState.nowPlayingMovies)

                recyclerViewNowPlaying.adapter = nowPlayingAdapter
                recyclerViewNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            is AppState.SuccessUpcoming -> {
                val upcomingAdapter = UpcomingAdapter(requireActivity())

                upcomingAdapter.setUpcomingMovies(appState.upcomingMovies)

                recyclerViewUpcoming.adapter = upcomingAdapter
                recyclerViewUpcoming.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}