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
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.ResultAdapter
import com.rentme.rentme.adapter.SearchAdapter
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.databinding.FragmentSearchBinding
import com.rentme.rentme.model.Result
import com.rentme.rentme.utils.UiStateList
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { SearchAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.getCarModels()

        setUpObservers()
        initViews()



    }


    //carModels  ni olib beradi
    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.carModels.collect {
                when (it) {
                    is UiStateList.LOADING -> {}
                    is UiStateList.SUCCESS -> {

                        adapter.setData(it.data)

                    }
                    is UiStateList.ERROR -> {
                        Log.d("SearchFragment", "Error:" + it.message)
                    }
                    else -> Unit
                }
            }
        }
    }


    private fun initViews() {

        setUpRecyclerView()
        setupUI()
        onItemClicked()
    }

    private fun setUpRecyclerView() {
        binding.rvSearch.adapter = adapter
    }

    private fun setupUI() {
        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
//
            }
        })
    }




    private fun onItemClicked() {
        adapter.onClick = { modelsListEntity ->
            setFragmentResult("model_name", bundleOf("data" to modelsListEntity.modelName))
            findNavController().navigate(R.id.resultFragment)
        }
    }
}
