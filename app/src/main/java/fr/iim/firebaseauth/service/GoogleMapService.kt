package fr.iim.firebaseauth.service

import android.provider.Settings.System.getString
import android.util.Log
import fr.iim.firebaseauth.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class GoogleMapService {

    fun getCity(cityName:String) {

        var reqParam = URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(cityName, "UTF-8")
        reqParam += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(/*R.string.google_maps_key.toString()*/"AIzaSyCX7qsH1rvcdSZHvWsJqUnHAP8DC1zXYnc", "UTF-8")

        //val mURL = URL("https://maps.googleapis.com/maps/api/geocode/json?adress=$reqParam" )
        val connection = URL("https://maps.googleapis.com/maps/api/geocode/json?adress=$reqParam").openConnection() as HttpURLConnection
        val data = connection.inputStream.bufferedReader().readText()
        println("Response Code : $data")
       /* with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            println("URL : $url")
            println("Response Code : $responseCode")
            Log.d("Map Response", responseMessage)

            mURL.openConnection().inputStream

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("Response : $response")
            }
        }*/
    }
}