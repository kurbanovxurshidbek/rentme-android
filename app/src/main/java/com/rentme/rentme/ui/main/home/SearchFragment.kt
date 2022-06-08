package com.rentme.rentme.ui.main.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rentme.rentme.databinding.FragmentSearchBinding
import com.rentme.rentme.utils.UiStateList
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding : FragmentSearchBinding
    private var carModels: ArrayList<String> = ArrayList()
    lateinit var input :String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        searchViewModel.getCarModels()
        setUpObservers()


          val textWatcher = object : TextWatcher{
             override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                 //get list from history
             }

             override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                 //searchByletters
             }

             override fun afterTextChanged(p0: Editable?) {
                 // nothing
             }
         }


    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.carModels.collect{
                when(it){
                    is UiStateList.LOADING -> {}
                    is UiStateList.SUCCESS -> {
                        it.data.forEach { model ->
                            carModels.add(model.modelName)
                        }
                    }
                    is UiStateList.ERROR -> {
                        Log.d("SearchFragment", "Error:" + it.message)
                    }
                    else -> Unit
                }
            }
        }
    }




}