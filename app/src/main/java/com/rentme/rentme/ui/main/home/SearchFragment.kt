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
import androidx.recyclerview.widget.GridLayoutManager
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
//        getAllSearchList()


    }

    private fun initViews() {

        setUpRecyclerView()
        setupUI()




    }

    private fun setupUI() {
        binding.edtSearch.addTextChangedListener(object :TextWatcher{
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


    private fun setUpRecyclerView() {
        binding.rvSearchHistory.adapter = adapter
    }

    private fun getAllSearchList() :ArrayList<ModelsListEntity>{
        var items: ArrayList<ModelsListEntity> = ArrayList()
        items.add(ModelsListEntity(1L,"Malibu"))
        items.add(ModelsListEntity(2L,"Mabibu"))
        items.add(ModelsListEntity(3L,"Masibu"))
        items.add(ModelsListEntity(4L,"Maedibu"))
        items.add(ModelsListEntity(5L,"Majibu"))
        items.add(ModelsListEntity(6L,"Maribu"))
        items.add(ModelsListEntity(7L,"Mauibu"))
        adapter.setData(items)
        return items
    }

    //carModels  ni olib beradi
    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.carModels.collect {
                when (it) {
                    is UiStateList.LOADING -> {}
                    is UiStateList.SUCCESS -> {

                        adapter.setData(getAllSearchList())
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
