package com.rentme.rentme.ui.profile


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rentme.rentme.databinding.FragmentProfileBinding
import com.rentme.rentme.ui.details.information.InformationActivity
import com.rentme.rentme.ui.favourite.FavouriteActivity
import com.rentme.rentme.ui.localization.LocalizationActivity
import com.rentme.rentme.ui.update.UpdateActivity

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.llMyData.setOnClickListener {
            startInformationActivity()
        }
        binding.llLanguage.setOnClickListener {
            startLocazilationActivity()
        }
        binding.llChangePhoneNumber.setOnClickListener {
            startUpdateActivity()
        }
        binding.llMyFavourite.setOnClickListener {
            startFavouriteActivity()
        }
        }
    fun startInformationActivity(){
        val intent = Intent(requireActivity(),InformationActivity::class.java)
        startActivity(intent)
    }
    fun startUpdateActivity(){
        val intent = Intent(requireActivity(),UpdateActivity::class.java)
        startActivity(intent)
    }
    fun startLocazilationActivity(){
        val intent = Intent(requireActivity(),LocalizationActivity::class.java)
        startActivity(intent)
    }
    fun startFavouriteActivity(){
        val intent = Intent(requireActivity(),FavouriteActivity::class.java)
        startActivity(intent)
    }

    }

