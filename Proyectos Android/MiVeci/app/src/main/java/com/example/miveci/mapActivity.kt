package com.example.miveci
import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class mapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var mMap: GoogleMap
    private val markers = mutableListOf<Marker>()

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

    //permisos para iniciar el mapa
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else {
            googleMap.isMyLocationEnabled = true
        }
        cargarMarcadoresDesdeArchivo()

        mMap.setOnCameraIdleListener {
            if (mMap.cameraPosition.zoom > 12) { // Change this threshold as needed
                for (marker in this.markers) {
                    marker.isVisible = true
                }
            } else {
                for (marker in this.markers) {
                    marker.isVisible = false
                }
            }
        }
    }


    //Se agregan marcadores desde un archivo txt
    private fun cargarMarcadoresDesdeArchivo() {
        try {
            val inputStream = resources.openRawResource(R.raw.tiendas)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                val parts = line?.split(",")
                if (parts?.size == 5) { // Verifica que haya 5 partes
                    val nombre = parts[0]
                    val lat = parts[1].toDouble()
                    val lng = parts[2].toDouble()

                    // Puedes agregar la ciudad y la localidad al título pero ocultarlas
                    val ciudad = parts[3]
                    val localidad = parts[4]
                    val tiendaLatLng = LatLng(lat, lng)

                    // Agregar marcador al mapa y a la lista de markers
                    val marker = mMap.addMarker(
                        MarkerOptions()
                            .position(tiendaLatLng)
                            .title(nombre)
                            .snippet("Ciudad: $ciudad, Localidad: $localidad") // Mostrar en el snippet si es necesario
                    )
                    if (marker != null) {
                        markers.add(marker)
                    }
                }
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}