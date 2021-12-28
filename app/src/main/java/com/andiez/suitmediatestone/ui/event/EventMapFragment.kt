package com.andiez.suitmediatestone.ui.event

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andiez.suitmediatestone.databinding.FragmentEventMapBinding
import com.andiez.suitmediatestone.di.Injection
import com.andiez.suitmediatestone.helper.EventSelectListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class EventMapFragment private constructor(private val listener: EventSelectListener) : Fragment(),
    OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var binding: FragmentEventMapBinding
    private lateinit var presenter: EventPresenter
    private fun initPresenter(): EventPresenter =
        Injection.provideEventPresenter(listener)

    override fun onAttach(context: Context) {
        presenter = initPresenter()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventMapBinding.inflate(inflater)
        with(binding) {
            map.onCreate(savedInstanceState)
            map.onResume()
            map.getMapAsync(this@EventMapFragment)
        }
        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.enableMyLocation(requireContext(), requireActivity())
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val latitude = -6.970190972774353
        val longitude = 107.630083237386
        val zoomLevel = 15f
        val fitLatLang =
            LatLng(latitude, longitude)
        map.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                fitLatLang,
                zoomLevel
            )
        )
        presenter.settingGridAdapterRecyclerview(
            binding.rvEvents,
            requireParentFragment(),
            requireContext(),
            map
        )
        presenter.onEventsAddMarker(map)
        presenter.enableMyLocation(requireContext(), requireActivity())
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
        fun newInstance(listener: EventSelectListener) = EventMapFragment(listener)
    }

}