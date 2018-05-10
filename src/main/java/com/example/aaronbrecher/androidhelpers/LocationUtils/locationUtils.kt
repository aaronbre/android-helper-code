package com.example.aaronbrecher.androidhelpers.LocationUtils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import java.io.IOException
import java.text.DecimalFormat

/**
 * Created by aaronbrecher on 5/9/18.
 */
fun hasLocationPermissions(context: Context): Boolean {
    return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
}

/**
 * Get the last known location of the user device, will only work if the
 * location was recently found
 */
@SuppressLint("MissingPermission")
fun getLastKnownLocation(context: Context): Location? {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    var location: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    return location
}

/**
 * calculate the distance between two location and return a string representaion
 * to the 10th of a kilometer
 */
public fun getPrettyDistance(location1: Location, location2: Location): String {
    val distance = location1.distanceTo(location2) / 1000
    val decimalFormat = DecimalFormat("#.#")
    return decimalFormat.format(distance)
}

/**
 * This function is used to get the location object (latitude & longitude)
 * from an address string
 */
fun locationFromAddress(address: String, context: Context): Location? {
    val coder = Geocoder(context)
    var addressList: List<Address>?
    val location: Location = Location(LocationManager.GPS_PROVIDER)
    try {
        addressList = coder.getFromLocationName(address, 5)
        if(addressList == null) return null
        val address = addressList[0]
        location.latitude = address.latitude
        location.longitude = address.longitude
    } catch (e: IOException){
        e.printStackTrace()
    }
    return location
}