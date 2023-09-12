package com.example.miveci
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class mapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            googleMap.isMyLocationEnabled = true
        }

        val tiendita1 = LatLng(4.7708774,-74.1317791)
        val marker = googleMap.addMarker(
            MarkerOptions()
                .position(tiendita1)
                .title("Restaurante Doña Sandra")
        )

        ajusteVisibilidadMarcador(googleMap, marker)
    }

    fun ajusteVisibilidadMarcador(googleMap: GoogleMap, marker: Marker){
        googleMap.setOnCameraIdleListener {
            val cameraPosition = googleMap.cameraPosition
            val distance = FloatArray(1)
            Location.distanceBetween(
                cameraPosition.target.latitude, cameraPosition.target.longitude,
                marker.position.latitude, marker.position.longitude, distance
            )
            if (distance[0] < 300) {
                marker.isVisible = true
            } else {
                marker.isVisible = false
            }
        }
    }

}