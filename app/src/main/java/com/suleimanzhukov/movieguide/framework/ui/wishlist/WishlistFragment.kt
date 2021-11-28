package com.suleimanzhukov.movieguide.framework.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.databinding.FragmentMainBinding
import com.suleimanzhukov.movieguide.databinding.FragmentWishlistBinding
import com.suleimanzhukov.movieguide.framework.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Appendable

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

    private fun renderData(appState: AppState) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = WishlistFragment()
    }
}