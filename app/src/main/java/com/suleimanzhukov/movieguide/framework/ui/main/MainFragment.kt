package com.suleimanzhukov.movieguide.framework.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.databinding.FragmentMainBinding
import com.suleimanzhukov.movieguide.framework.adapters.NowPlayingAdapter
import com.suleimanzhukov.movieguide.framework.adapters.UpcomingAdapter
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
        mainViewModel.getMainLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        mainViewModel.getMainData()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                val nowPlayingAdapter = NowPlayingAdapter(requireActivity())
                val upcomingAdapter = UpcomingAdapter(requireActivity())

                nowPlayingAdapter.setNowPlayingMovies(appState.movies)
                upcomingAdapter.setUpcomingMovies(appState.movies)

                recyclerViewNowPlaying.adapter = nowPlayingAdapter
                recyclerViewUpcoming.adapter = upcomingAdapter
                recyclerViewNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewUpcoming.layoutManager = LinearLayoutManager(context)
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
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