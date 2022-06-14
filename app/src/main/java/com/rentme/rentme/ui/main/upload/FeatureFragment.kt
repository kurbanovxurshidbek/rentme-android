package com.rentme.rentme.ui.main.upload

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rentme.rentme.R
import com.rentme.rentme.adapter.CarImageAdapter
import com.rentme.rentme.adapter.ColorAdapter
import com.rentme.rentme.databinding.FragmentFeaturesBinding
import com.rentme.rentme.model.*
import com.rentme.rentme.utils.Extensions
import com.rentme.rentme.utils.SelectColor
import com.rentme.rentme.utils.UiStateObject
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.time.Year


@AndroidEntryPoint
class FeatureFragment : Fragment() {
    private val viewModel: FeatureViewModel by viewModels()

    private val colorAdapter by lazy { ColorAdapter() }
    private lateinit var carImageAdapter: CarImageAdapter
    private val TAG = this::class.java.simpleName

    private var _binding: FragmentFeaturesBinding? = null
    private val binding get() = _binding!!

    private var uploadAdvertisement: UploadAdvertisement? = null
    private var allPhotos: ArrayList<Uri> = ArrayList()
    private var carImages: ArrayList<Uri> = ArrayList()
    private var carImageUrls: ArrayList<String> = ArrayList()
    private var selectModelName: String = ""
    private var selectColorName: String = ""
    private var selectYear: String = ""
    private val prices: ArrayList<Price> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uploadAdvertisement = Gson().fromJson(it.getString("uploadAdvertisement"), UploadAdvertisement::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFeaturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLocation.text = uploadAdvertisement?.location?.name
        binding.tvStartDate.text = uploadAdvertisement?.startDate.toString()
        if (uploadAdvertisement?.minDuration!! < 30){
            binding.minDayCount.text = uploadAdvertisement?.minDuration.toString()
            binding.minTypeTime.text = getString(R.string.str_days)
        }
        else{
            binding.minDayCount.text = (uploadAdvertisement?.minDuration!! / 30).toString()
            binding.minTypeTime.text = getString(R.string.str_months)
        }

        selectModelSpinner()
        selectYearSpinner()
        allColorFunction()
        enterPriceFunction()

        binding.btnSave.setOnClickListener {
            openMyAddsFragment()
        }
        carImageAdapter = CarImageAdapter()
        carImageAdapter.items.add(null)
        binding.rvCarPhotos.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        binding.rvCarPhotos.adapter = carImageAdapter
        carImages.add(Uri.EMPTY)
        carImageAdapter.clickAddCarImage = {
            if (carImages.size < 9) pickFishBunCarImages()
        }
        carImageAdapter.clickClear = { position ->
            carImageUrls.removeAt(position-1)
            carImages.removeAt(position)
        }

        setupObservers()

    }

    private fun setupObservers(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.fileState.collect{
                when(it){
                    is UiStateObject.LOADING -> {}
                    is UiStateObject.SUCCESS -> {
                        carImageUrls.addAll(it.data.data.data)
                        for (s in carImageUrls){
                            Log.d("TAGurl", "URL: $s")
                        }
                        carImageAdapter.items.clear()
                        carImageAdapter.saveCarImageStorage(carImages)
                    }
                    is UiStateObject.ERROR ->{
                        Log.d(TAG, "Error:" + it.message) }
                    else -> Unit
                }
            }
        }

        // The best Launch launchWhenStarted in (launchWhenCreated, launchWhenStarted, launchWhenResumed)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.featureState.collect{
                    when(it){
                        is UiStateObject.LOADING -> {}
                        is UiStateObject.SUCCESS -> {
                            Toast.makeText(requireContext(), "This Advertisement is Created", Toast.LENGTH_SHORT).show()
                            findNavController().navigateUp()
                            findNavController().navigateUp()
                            findNavController().navigate(R.id.myAddsFragment)
                        }
                        is UiStateObject.ERROR -> {
                            Log.d(TAG, "Error:" + it.message)
                        }
                        else -> Unit
                    }
                }
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    private fun openMyAddsFragment(){
        val checkPrice = ((binding.llDailyPrice.visibility == View.VISIBLE && binding.edtPriceDaily.text.isNotEmpty())
                || binding.llDailyPrice.visibility == View.GONE)
                && ((binding.llMonthlyPrice.visibility == View.VISIBLE && binding.edtPriceMonthly.text.isNotEmpty())
                || binding.llMonthlyPrice.visibility == View.GONE)
        if (checkPrice && carImageUrls.isNotEmpty() && carImageUrls.size > 1 && carImageUrls.size == carImages.size - 1){
            prices.clear()
            if (binding.llDailyPrice.visibility == View.VISIBLE)
                prices.add(Price(binding.edtPriceDaily.text.toString().toInt(), Type.DAILY))
            if (binding.llMonthlyPrice.visibility == View.VISIBLE)
                prices.add(Price(binding.edtPriceMonthly.text.toString().toInt(), Type.MONTHLY))
            val transport = TransportUpload( selectModelName, selectYear.toInt()
            ,selectManagementSystem(), selectFuelType(), selectColorName, selectAllImageUrls(carImageUrls), checkAdditional())
            uploadAdvertisement?.description = binding.edtDescription.text.toString()
            uploadAdvertisement?.prices = prices
            uploadAdvertisement?.transport = transport
            val timeStamp = Extensions.toTimestamp(binding.tvStartDate.text.toString(), "dd-MM-yyyy")
            uploadAdvertisement?.startDate = Extensions.toDateFromTimestamp(timeStamp, "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
            viewModel.createAdvertisement(uploadAdvertisement!!)
            Log.d(TAG, "openMyAddsFragment: $uploadAdvertisement")
        }else{
            Toast.makeText(requireContext(), getString(R.string.str_fill_all_fields), Toast.LENGTH_SHORT).show()
        }
    }



    /**
     * Pick photo using FishBun library
     */
    private fun pickFishBunCarImages(){
        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(10)
            .setMinCount(1)
            .setCamera(true)
            .setSelectedImages(allPhotos)
            .startAlbumWithActivityResultCallback(photoLauncher)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val photoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            allPhotos = it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH) ?: arrayListOf()
            carImages.addAll(allPhotos)
            carImageAdapter.items.clear()
            carImageAdapter.state = false
            carImageAdapter.items.addAll(carImages)

            val imageFiles = getFileList(allPhotos)
            viewModel.createFile(imageFiles)

            allPhotos.clear()
            carImageAdapter.notifyDataSetChanged()
        }
    }

    private fun enterPriceFunction(){
        if (uploadAdvertisement?.minDuration!! < 30) binding.llDailyPrice.visibility = View.VISIBLE
        else binding.llDailyPrice.visibility = View.GONE

        if (uploadAdvertisement?.maxDuration!! > 30) binding.llMonthlyPrice.visibility = View.VISIBLE
        else binding.llMonthlyPrice.visibility = View.GONE
    }

    private fun allColorFunction(){
        val colors: ArrayList<Int> = ArrayList()
        colors.add(R.color.car_black)
        colors.add(R.color.car_meteor_grey)
        colors.add(R.color.car_bright_white)
        colors.add(R.color.car_candy_white)
        colors.add(R.color.car_brilliant_silver)
        colors.add(R.color.car_energy_blue)
        colors.add(R.color.car_race_blue)
        colors.add(R.color.car_velvet_red)
        colors.add(R.color.car_corrida_red)
        colors.add(R.color.car_yellow)
        colors.add(R.color.car_orange)
        colorAdapter.submitData(colors)

        binding.rvColors.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvColors.adapter = colorAdapter
        colorAdapter.onClick = {color ->
            selectColorName = SelectColor.codeToName(color)
            binding.tvColorName.text = selectColorName
        }
    }

    private fun selectModelSpinner(){
        val models: ArrayList<String> = ArrayList()
        models.add("Subaru Outback")
        models.add("Suzuki Ciaz")
        models.add("Malibu")
        models.add("Captiva")
        models.add("Gentra")
        models.add("Lacetti")
        models.add("Spark")
        models.add("Camaro")
        models.add("Blazer")
        models.add("Tahoe")
        models.add("Tracker")
        models.add("Trailblazer")
        models.add("Matiz")
        models.add("Vintage")
        models.add("Tico")

        binding.spnModels.adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item_view, models)
        binding.spnModels.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectModelName = p0!!.getItemAtPosition(p2).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun selectYearSpinner(){
        val currentYear = Year.now().value
        val years: ArrayList<String> = ArrayList()
        for (i in currentYear downTo 2000){
            years.add(i.toString())
        }
        binding.spnYear.adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item_view, years)
        binding.spnYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectYear = p0!!.getItemAtPosition(p2).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun selectManagementSystem(): Transmission{
        val selectedId: Int = binding.systemRadioGroup.checkedRadioButtonId
        if (selectedId == R.id.system_radio_mechanical) return Transmission.MANUAL
        return Transmission.AUTOMATIC
    }

    private fun selectFuelType(): FuelType{
        val selectedId: Int = binding.fuelRadioGroup.checkedRadioButtonId
        if (selectedId == R.id.fuel_radio_petrol) return FuelType.PETROL
        return FuelType.GAS
    }

    private fun selectAllImageUrls(images: ArrayList<String>) : ArrayList<Picture>{
        val imageUrls: ArrayList<Picture> = ArrayList()
        for (image in images){
            imageUrls.add(Picture(image, true))
        }
        return imageUrls
    }

    private fun checkAdditional() : Boolean{
        return (binding.chbConditioners.isChecked || binding.chbRadio.isChecked)
    }

    private fun getFileList(uris: ArrayList<Uri>) : ArrayList<MultipartBody.Part>{
        val files: ArrayList<MultipartBody.Part> = ArrayList()
        for (uri in uris){
            files.add(getFile(uri))
        }
        return files
    }

    private fun getFile(uri: Uri): MultipartBody.Part {
        val ins = requireContext().contentResolver.openInputStream(uri)

        val file = File.createTempFile(
            "file", ".jpg",
            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        val fileOutputStream = FileOutputStream(file)
        ins?.copyTo(fileOutputStream)
        ins?.close()
        fileOutputStream.close()
        val reqFile = RequestBody.create("image/jpg".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("file", file.name, reqFile)
    }

}