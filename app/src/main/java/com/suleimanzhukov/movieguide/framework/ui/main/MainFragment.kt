package com.suleimanzhukov.movieguide.framework.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.databinding.FragmentMainBinding
import com.suleimanzhukov.movieguide.framework.adapters.NowPlayingAdapter
import org.intellij.lang.annotations.JdkConstants

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val nowPlayingAdapter = NowPlayingAdapter(object : OnItemClickListener {
                override fun onMovieClickListener() {

                }
            })
            recyclerViewNowPlaying.adapter = nowPlayingAdapter
            recyclerViewNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnItemClickListener {
        fun onMovieClickListener()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}