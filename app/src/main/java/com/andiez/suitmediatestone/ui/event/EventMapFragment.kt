package com.andiez.suitmediatestone.ui.event

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andiez.suitmediatestone.databinding.FragmentEventMapBinding
import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.ui.SharedMainViewModel
import com.andiez.suitmediatestone.viewmodel.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class EventMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var binding: FragmentEventMapBinding
    private val markers = ArrayList<Marker?>()
    private val viewModel: SharedMainViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private lateinit var adapter: EventGripAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventMapBinding.inflate(inflater)
        adapter = EventGripAdapter(object : EventGripAdapter.GridHandler {
            override fun onItemClick(position: Int, item: EventEntity) {
                viewModel.setSelectedEvent(item)
                requireParentFragment().findNavController().popBackStack()
            }
        })
        adapter.setListNotes(viewModel.events)
        with(binding) {
            val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvEvents.layoutManager = layoutManager
            rvEvents.setHasFixedSize(true)
            rvEvents.adapter = adapter
            rvEvents.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val position = layoutManager.findFirstCompletelyVisibleItemPosition()
                        if (position > -1) {
                            val item = adapter.getItemAt(position)
                            val marker = markers[position]
                            this@EventMapFragment.map.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(item.lat, item.lon),
                                    15f
                                )
                            )
                            marker?.showInfoWindow()
                        }
                    }
                }
            })
        }
        binding.map.onCreate(savedInstanceState)
        binding.map.onResume()
        binding.map.getMapAsync(this)
        return binding.root
    }

    private fun onEventsAddMarker(map: GoogleMap) {
        viewModel.events.forEach { event ->
            val marker = map.addMarker(
                MarkerOptions().position(LatLng(event.lat, event.lon)).title(event.name)
            )
            markers.add(marker)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation()
            }
        }
    }

    private fun enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
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
        onEventsAddMarker(map)
        enableMyLocation()
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
        fun newInstance() = EventMapFragment()
    }

}