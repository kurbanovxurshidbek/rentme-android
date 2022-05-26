package com.rentme.rentme.ui.profile


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.rentme.rentme.databinding.FragmentProfileBinding
import com.rentme.rentme.ui.details.information.InformationActivity
import com.rentme.rentme.ui.localization.LocalizationActivity

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
        binding.llLogOut.setOnClickListener {
            showLogOutDialog()
        }
        binding.llAboutUs.setOnClickListener {
            showInfoAboutAppDialog()
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
    private fun showLogOutDialog(){
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage("Do you want to Log out")
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
                activity?.finish()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Log out")
        alert.show()
    }
    private fun showInfoAboutAppDialog(){
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage("This is a test application for renting cars, and help people to reach their destination with the help of others. ")
            // if the dialog is cancelable
            .setCancelable(false)

            .setNeutralButton("Ok") { dialog, id ->
                dialog.dismiss()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Info")
        alert.show()
    }


}

