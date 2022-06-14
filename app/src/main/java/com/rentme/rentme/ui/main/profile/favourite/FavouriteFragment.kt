package com.rentme.rentme.ui.main.profile.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.FavouriteAdapter
import com.rentme.rentme.databinding.FragmentFavouriteBinding
import com.rentme.rentme.databinding.FragmentResultBinding
import com.rentme.rentme.databinding.FragmentSearchBinding
import com.rentme.rentme.model.Result
import com.rentme.rentme.utils.UiStateList
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {
    private val viewModel: FavouriteViewModel by viewModels()
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { FavouriteAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Toast.makeText(requireContext(), list.toString(), Toast.LENGTH_SHORT).show()
        viewModel.getFavouriteModels()
        initViews()
        setUpObservers()
    }

    private fun initViews() {
        binding.rvFavourites.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.rvFavourites.adapter = adapter
        adapter.onClick = { result ->
            onItemClicked(result.id)  // send the id to details fragment
            findNavController().navigate(R.id.detailsFragment)
        }
        adapter.unLikeFavourite = {
            //unlike one favourite
        }

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }



    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.favouriteModels.collect {
                when (it) {
                    is UiStateObject.LOADING -> {}
                    is UiStateObject.SUCCESS -> {

                        adapter.submitData(it.data.data.data)

                    }
                    is UiStateObject.ERROR -> {
                        Log.d("SearchFragment", "Error:" + it.message)
                    }
                    else -> Unit
                }
            }
        }
    }
    private fun onItemClicked(id: Long?) {
        setFragmentResult("from_favourites", bundleOf("data" to id))
    }
}





