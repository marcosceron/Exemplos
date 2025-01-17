package com.example.exemplos

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class GeoLocalizacaoActivity: AppCompatActivity() {
    val ID_REQUISICAO_FINE_LOCATION = 101

    lateinit var locationListener:LocationListener



    class MyLocationListener: LocationListener {
        override fun onLocationChanged(location: Location) {
            TODO("Not yet implemented")
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderDisabled(provider: String) {
            super.onProviderDisabled(provider)
        }

        override fun onProviderEnabled(provider: String) {
            super.onProviderEnabled(provider)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_localizacao)

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = MyLocationListener()
        val btnLocalizacao = findViewById<Button>(R.id.btn_localizacao)
        val btnParar = findViewById<Button>(R.id.btn_parar)

        btnLocalizacao.setOnClickListener{
            requisitarLocalizacao()
        }

        btnParar.setOnClickListener{
            locationManager.removeUpdates(locationListener)
        }

    }

    fun requisitarLocalizacao() {
        val permissao = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager


        if (permissao == PackageManager.PERMISSION_GRANTED) {
            val tempoAtualizacao:Long = 0
            val distanciaAtualizacao:Float = 0f

            //Obtendo dados de localizaçõa da rede
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                tempoAtualizacao,
                distanciaAtualizacao,
                locationListener
            )

            //Obtendo dados de localização do GPS
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                tempoAtualizacao,
                distanciaAtualizacao,
                locationListener
            )


            //Obtendo dados usando provedor passivo
            locationManager.requestLocationUpdates(
                LocationManager.PASSIVE_PROVIDER,
                tempoAtualizacao,
                distanciaAtualizacao,
                locationListener
            )

        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                ID_REQUISICAO_FINE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        if(requestCode == ID_REQUISICAO_FINE_LOCATION) {
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requisitarLocalizacao()
            }
            else {
                //A permissão não foi concedida, aqui você deverá desabilitar a funcionalidade que utiliza tal recurso
                Toast.makeText(this, "Permissão não concedida", Toast.LENGTH_LONG)
            }
        }
    }

}