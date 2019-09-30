package com.robnewport.codesample.Activities

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.robnewport.codesample.DataClasses.VehicleBooking
import com.robnewport.codesample.R
import com.robnewport.codesample.Fragments.DetailFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);

        val booking = intent.getSerializableExtra(BOOKING_DETAILS_KEY) as VehicleBooking

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(booking))
                .commitNow()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private val BOOKING_DETAILS_KEY = "DETAILS"
    }
}