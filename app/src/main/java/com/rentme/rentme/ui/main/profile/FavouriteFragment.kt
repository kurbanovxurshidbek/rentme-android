package com.rentme.rentme.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rentme.rentme.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {
    private lateinit var binding : FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


}