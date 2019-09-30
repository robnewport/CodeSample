package com.robnewport.codesample.Utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL


class DownloadVehicleImageTask(var image: ImageView) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg urls: String): Bitmap? {
        val urlString = urls[0]
        var bmp: Bitmap? = null
        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input = connection.getInputStream()
            return BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            val error = e.message
            if (error is String) {
                Log.e("Error", error)
                e.printStackTrace()
            }
        }
        return bmp
    }

    override fun onPostExecute(result: Bitmap) {
        image.setImageBitmap(result)
    }

}