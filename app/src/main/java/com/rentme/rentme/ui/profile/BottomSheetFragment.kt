package com.rentme.rentme.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rentme.rentme.R
import com.rentme.rentme.databinding.BottomSheetLocaliztionBinding

class BottomSheetFragment:BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetLocaliztionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLocaliztionBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            binding.uzbLanguage.setOnClickListener {
                Toast.makeText(requireActivity(), "O`zbek tili tanlandi", Toast.LENGTH_SHORT).show()
            }
        binding.rusLanguage.setOnClickListener {
            Toast.makeText(requireActivity(), "Выбрано Русский язык", Toast.LENGTH_SHORT).show()

        }
        binding.engLanguage.setOnClickListener {
            Toast.makeText(requireActivity(), "English language selected", Toast.LENGTH_SHORT).show()
        }
    }


}