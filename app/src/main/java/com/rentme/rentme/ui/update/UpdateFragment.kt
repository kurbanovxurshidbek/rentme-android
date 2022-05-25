package com.rentme.rentme.ui.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rentme.rentme.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {
    private lateinit var binding : FragmentUpdateBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSendSms.setOnClickListener {
            sendSmsToUpdateNumber()
        }
        binding.btnSaveSms.setOnClickListener {
            saveUpdatedNumber()
        }
        binding.ivBack.setOnClickListener { activity?.finish() }
    }


    private fun saveUpdatedNumber() {
        Toast.makeText(requireActivity(), "Phone number changed successfully", Toast.LENGTH_SHORT).show()
        activity?.finish()
    }

    private fun sendSmsToUpdateNumber() {
        binding.llCurrentNumber.visibility = View.GONE
        binding.llNewNumber1.visibility = View.GONE
        binding.btnSendSms.visibility = View.GONE
        binding.btnSaveSms.visibility = View.VISIBLE
        binding.llNewNumber2.visibility = View.VISIBLE
        binding.llSmsConsfirm.visibility = View.VISIBLE
    }

}



