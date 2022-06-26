package com.rentme.rentme.ui.main.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentProfileBinding
import com.rentme.rentme.ui.main.MainActivity

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
            setFragmentResult("from_profile_page", bundleOf("data" to "from_profile_page"))
//            val intent = Intent(activity,LoginActivity::class.java)
//            startActivity(intent)
        }
        binding.llLanguage.setOnClickListener {
            (requireActivity() as MainActivity).showBottomSHeetFragment()

        }
        binding.llChangePhoneNumber.setOnClickListener {
            findNavController().navigate(R.id.updateFragment)
        }
        binding.llMyFavourite.setOnClickListener {
            findNavController().navigate(R.id.favouriteFragment)
        }
        binding.llLogOut.setOnClickListener {
            (requireActivity() as MainActivity).showLogOutBottomSheet()
        }
        binding.llAboutUs.setOnClickListener {
            showInfoAboutAppDialog()
        }
        }
    private fun startInformationActivity(){
//        val intent = Intent(requireActivity(), InformationActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.informationFragment2)
    }
    private fun startUpdateActivity(){
        findNavController().navigate(R.id.updateFragment)
    }
    fun startLocazilationActivity(){
//        val intent = Intent(requireActivity(),LocalizationActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.localizationFragment)
    }
    fun startFavouriteActivity(){
        findNavController().navigate(R.id.favouriteFragment)
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

