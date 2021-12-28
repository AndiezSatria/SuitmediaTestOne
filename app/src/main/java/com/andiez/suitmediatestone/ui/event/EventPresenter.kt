package com.andiez.suitmediatestone.ui.event

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andiez.suitmediatestone.R
import com.andiez.suitmediatestone.helper.EventSelectListener
import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.ui.base.BasePresenter
import com.andiez.suitmediatestone.utils.DataDummy
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class EventPresenter private constructor(private var listener: EventSelectListener) :
    BasePresenter() {
    private var fragmentPosition = 1
    val events: List<EventEntity> = DataDummy.getEventsDummy()
    private val markers = ArrayList<Marker?>()

    fun onEventsAddMarker(map: GoogleMap) {
        events.forEach { event ->
            val marker = map.addMarker(
                MarkerOptions().position(LatLng(event.lat, event.lon)).title(event.name)
            )
            markers.add(marker)
        }
    }

    fun settingAdapterRecyclerview(rv: RecyclerView, parentFragment: Fragment) {
        val listAdapter = EventsAdapter(object : EventsAdapter.EventItemHandler {
            override fun onItemClick(position: Int, item: EventEntity) {
                listener.onEventSelected(item)
                parentFragment.findNavController().popBackStack()
            }
        })
        listAdapter.setListNotes(events)
        rv.setHasFixedSize(true)
        rv.adapter = listAdapter
    }

    fun settingGridAdapterRecyclerview(
        rv: RecyclerView,
        parentFragment: Fragment,
        context: Context,
        map: GoogleMap
    ) {
        val adapter = EventGripAdapter(object : EventGripAdapter.GridHandler {
            override fun onItemClick(position: Int, item: EventEntity) {
                listener.onEventSelected(item)
                parentFragment.findNavController().popBackStack()
            }
        })
        adapter.setListNotes(events)
        val layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv.layoutManager = layoutManager
        rv.setHasFixedSize(true)
        rv.adapter = adapter
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val position = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (position > -1) {
                        val item = adapter.getItemAt(position)
                        val marker = markers[position]
                        map.moveCamera(
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

    fun setFragment(manager: FragmentManager) {
        val fragment = when (fragmentPosition) {
            1 -> {
                fragmentPosition = 2
                ListEventFragment.getInstance(listener)
            }
            else -> {
                fragmentPosition = 1
                EventMapFragment.newInstance(listener)
            }
        }
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    fun enableMyLocation(context: Context, fragmentActivity: FragmentActivity) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                fragmentActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                EventMapFragment.REQUEST_LOCATION_PERMISSION
            )
        }
    }

    companion object {
        @Volatile
        private var instance: EventPresenter? = null

        fun getInstance(listener: EventSelectListener): EventPresenter =
            instance ?: synchronized(this) {
                instance ?: EventPresenter(listener)
            }
    }
}