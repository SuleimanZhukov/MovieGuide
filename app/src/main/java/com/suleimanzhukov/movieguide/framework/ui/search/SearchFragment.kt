package com.suleimanzhukov.movieguide.framework.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.databinding.FragmentSearchBinding
import com.suleimanzhukov.movieguide.framework.MainActivity
import com.suleimanzhukov.movieguide.framework.OnItemClickListener
import com.suleimanzhukov.movieguide.framework.adapters.NowPlayingAdapter
import com.suleimanzhukov.movieguide.framework.adapters.SearchAdapter
import com.suleimanzhukov.movieguide.framework.adapters.UpcomingAdapter
import com.suleimanzhukov.movieguide.framework.ui.details.DetailsFragment
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModel()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var searchText: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchText = arguments?.getString(SEARCH_KEY)
        searchViewModel.getMovies(searchText!!)
        searchViewModel.getSearchLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {

            }
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.SuccessNowPlaying -> {

            }
            is AppState.SuccessUpcoming -> {

            }
            is AppState.SuccessSearch -> {
                val searchAdapter = SearchAdapter(object : OnItemClickListener {
                    override fun onMovieClickListener(position: Int) {
                        val bundle = Bundle().apply {
                            putParcelable(DetailsFragment.DETAILS_KEY, appState.searchMovies[position])
                        }

                        activity?.supportFragmentManager!!
                            .beginTransaction()
                            .replace(R.id.container_de_fragmento, DetailsFragment.newInstance(bundle))
                            .addToBackStack("")
                            .commitAllowingStateLoss()
                    }
                })

                searchAdapter.setSearchResultMovies(appState.searchMovies)

                search_result_recycler_view.adapter = searchAdapter
                search_result_recycler_view.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SEARCH_KEY = "SEARCH_KEY"

        fun newInstance(bundle: Bundle): SearchFragment {
            var fragment = SearchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}