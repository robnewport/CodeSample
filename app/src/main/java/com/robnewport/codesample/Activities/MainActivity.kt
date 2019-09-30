package com.robnewport.codesample.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robnewport.codesample.R
import com.robnewport.codesample.Fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}
