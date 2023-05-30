package com.mfarhan08a.hangoutyuk.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfarhan08a.hangoutyuk.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"
    }
}