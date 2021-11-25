package com.suleimanzhukov.movieguide.framework.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.databinding.FragmentMainBinding
import com.suleimanzhukov.movieguide.framework.OnItemClickListener
import com.suleimanzhukov.movieguide.framework.adapters.NowPlayingAdapter
import com.suleimanzhukov.movieguide.framework.ui.details.DetailsFragment
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

                recyclerViewNowPlaying.adapter = nowPlayingAdapter
                recyclerViewNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {

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