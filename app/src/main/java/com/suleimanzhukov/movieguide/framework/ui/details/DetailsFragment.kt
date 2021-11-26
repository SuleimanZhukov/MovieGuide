package com.suleimanzhukov.movieguide.framework.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.suleimanzhukov.movieguide.AppState
import com.suleimanzhukov.movieguide.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private val detailsViewModel: DetailsViewModel by viewModel()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsViewModel.getDetailsLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        detailsViewModel.getDetailsData(arguments?.getString(DETAILS_KEY) ?: "")
    }

    private fun renderData(appState: AppState) = with(binding) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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