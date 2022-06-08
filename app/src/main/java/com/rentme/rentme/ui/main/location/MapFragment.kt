package com.rentme.rentme.ui.main.location

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentSender
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.mancj.materialsearchbar.MaterialSearchBar
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentMapBinding
import java.util.*

class MapFragment : Fragment() {

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback{ googleMap ->
        mMap = googleMap
        mMap.isMyLocationEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true

        if (mapView != null && mapView.findViewById<View?>("1".toInt()) != null) {
            val locationButton =
                (mapView.findViewById<View>("1".toInt()).parent as View).findViewById<View>("2".toInt())
            val layoutParams = locationButton.layoutParams as RelativeLayout.LayoutParams
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
            layoutParams.setMargins(0, 0, 40, 180)
        }
        //check if gps is enabled or not and then request user to enable it
        //check if gps is enabled or not and then request user to enable it
        val locationRequest: LocationRequest = LocationRequest.create()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val settingsClient = LocationServices.getSettingsClient(requireActivity())
        val task: Task<LocationSettingsResponse> =
            settingsClient.checkLocationSettings(builder.build())

        task.addOnSuccessListener(requireActivity(),
            OnSuccessListener<LocationSettingsResponse?> {
                getDeviceLocation()
            })

        task.addOnFailureListener(requireActivity(),
            OnFailureListener { e ->
                if (e is ResolvableApiException) {
                    val resolvable = e as ResolvableApiException
                    try {
                        resolvable.startResolutionForResult(requireActivity(), 51)
                    } catch (e1: IntentSender.SendIntentException) {
                        e1.printStackTrace()
                    }
                }
            })
        mMap.setOnMyLocationButtonClickListener {
            if (materialSearchBar.isSuggestionsVisible) materialSearchBar.clearSuggestions()
            if (materialSearchBar.isSearchOpened) materialSearchBar.closeSearch()
            false
        }
    }

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var placesClient: PlacesClient
    private lateinit var predictionList: List<AutocompletePrediction>

    private lateinit var mLastKnownLocation: Location
    private lateinit var locationCallback: LocationCallback

    private lateinit var materialSearchBar: MaterialSearchBar
    private lateinit var mapView: View
    private lateinit var bFind: Button

    private lateinit var myAddress: String

    private val DEFAULT_ZOOM = 15f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViews() {
        bFind = binding.layoutMap.bFind
        materialSearchBar = binding.layoutMap.searchBar

//        val mapFragment = fragmentManager?.findFragmentById(R.id.f_map) as SupportMapFragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.f_map) as SupportMapFragment
        mapFragment.getMapAsync(callback)
        mapView = mapFragment.requireView()

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        Places.initialize(requireContext(), "AIzaSyDh8sW4xizVRFWrj6E2KcMsoZyUMF2hTxQ")
        placesClient = Places.createClient(requireContext())
        val token = AutocompleteSessionToken.newInstance()

        materialSearchBar.setOnSearchActionListener(object :
            MaterialSearchBar.OnSearchActionListener {
            override fun onSearchStateChanged(enabled: Boolean) {

            }

            override fun onSearchConfirmed(text: CharSequence?) {
                requireActivity().startSearch(text.toString(), true, null, true)
            }

            override fun onButtonClicked(buttonCode: Int) {
                if (buttonCode == MaterialSearchBar.BUTTON_NAVIGATION) {
                    //opening or closing a navigation drawer
                } else if (buttonCode == MaterialSearchBar.BUTTON_BACK) {
                    materialSearchBar.clearSuggestions()
                }
            }

        })
        materialSearchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val predictionsRequest = FindAutocompletePredictionsRequest.builder()
                    .setCountry("uz")
                    .setTypeFilter(TypeFilter.REGIONS)
                    .setSessionToken(token)
                    .setQuery(s.toString())
                    .build()
                placesClient.findAutocompletePredictions(predictionsRequest)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val predictionsResponse = task.result
                            if (predictionsResponse != null) {
                                predictionList = predictionsResponse.autocompletePredictions
                                val suggestionsList: MutableList<String?> =
                                    ArrayList()
                                for (i in predictionList.indices) {
                                    val prediction = predictionList[i]
                                    suggestionsList.add(prediction.getFullText(null).toString())
                                }
                                materialSearchBar.updateLastSuggestions(suggestionsList)
                                if (!materialSearchBar.isSuggestionsVisible) {
                                    materialSearchBar.showSuggestionsList()
                                }
                            }
                        } else {
                            Log.i(
                                "mytag",
                                "${task.exception} prediction fetching task unsuccessful"
                            )
                        }
                    }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        materialSearchBar.setSuggestionsClickListener(object :
            SuggestionsAdapter.OnItemViewClickListener {
            override fun OnItemClickListener(position: Int, v: View) {
                if (position >= predictionList.size) {
                    return
                }
                val selectedPrediction = predictionList[position]
                val suggestion = materialSearchBar.lastSuggestions[position].toString()
                materialSearchBar.text = suggestion
                Handler().postDelayed(Runnable { materialSearchBar.clearSuggestions() }, 1000)
                val imm: InputMethodManager =
                    requireContext().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                if (imm != null) imm.hideSoftInputFromWindow(
                    materialSearchBar.windowToken,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
                val placeId = selectedPrediction.placeId
                val placeFields: List<Place.Field> = listOf(Place.Field.LAT_LNG, Place.Field.NAME, Place.Field.ADDRESS)
                val fetchPlaceRequest = FetchPlaceRequest.builder(placeId, placeFields).build()
                placesClient.fetchPlace(fetchPlaceRequest)
                    .addOnSuccessListener { fetchPlaceResponse ->
                        val place = fetchPlaceResponse.place
                        Log.i("mytag", "Place found: " + place.address)
                        myAddress = place.address ?: "Can't find your location"
                        val latLngOfPlace = place.latLng
                        if (latLngOfPlace != null) {
                            mMap.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    latLngOfPlace,
                                    DEFAULT_ZOOM
                                )
                            )
                        }
                    }.addOnFailureListener { e ->
                        if (e is ApiException) {
                            val apiException = e as ApiException
                            apiException.printStackTrace()
                            val statusCode = apiException.statusCode
                            Log.i("mytag", "place not found: " + e.message)
                            Log.i("mytag", "status code: $statusCode")
                        }
                    }
            }

            override fun OnItemDeleteListener(position: Int, v: View) {}
        })

        bFind.setOnClickListener(View.OnClickListener {
            val currentMarkerLocation = mMap.cameraPosition.target

            val addresses: List<Address>
            val geocoder: Geocoder = Geocoder(requireContext(), Locale.getDefault())

            addresses = geocoder.getFromLocation(
                currentMarkerLocation.latitude,
                currentMarkerLocation.longitude,
                1
            ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5


            val address: String =
                addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

//            val city: String = addresses[0].locality!!
//            val state: String = addresses[0].adminArea!!
//            val country: String = addresses[0].countryName!!
//            val postalCode: String = addresses[0].postalCode!!
//            val knownName: String =
//                addresses[0].featureName!! // Only if available else return NULL

            Log.i("mytag", "Address is : $address ${addresses[0].locality}, ${addresses[0].countryName}")
            myAddress = address

//            rippleBg.startRippleAnimation()
//            Handler().postDelayed({
//                rippleBg.stopRippleAnimation()
//                startActivity(Intent(this@MapActivity, MainActivity::class.java))
//                finish()
//            }, 3000)

            val result = Bundle().apply {
                putString("location", myAddress)
            }
            setFragmentResult("locationResult", result)
            findNavController().navigateUp()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 51) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                getDeviceLocation()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        mFusedLocationProviderClient.lastLocation
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    mLastKnownLocation = it.result
                    if (mLastKnownLocation != null) {
                        mMap.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(
                                    mLastKnownLocation.latitude,
                                    mLastKnownLocation.longitude
                                ), DEFAULT_ZOOM
                            )
                        )
                    } else {
                        val locationRequest = LocationRequest.create()
                        locationRequest.interval = 10000
                        locationRequest.fastestInterval = 5000
                        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        locationCallback = object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult) {
                                super.onLocationResult(locationResult)
                                mLastKnownLocation = locationResult.lastLocation
                                mMap.moveCamera(
                                    CameraUpdateFactory.newLatLngZoom(
                                        LatLng(
                                            mLastKnownLocation.latitude,
                                            mLastKnownLocation.longitude
                                        ), DEFAULT_ZOOM
                                    )
                                )
                                mFusedLocationProviderClient.removeLocationUpdates(locationCallback)
                            }
                        }
//                        mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback)

                    }
                } else {
                    Toast.makeText(requireContext(), "unable to get location", Toast.LENGTH_SHORT).show()
                }
            }
    }
}