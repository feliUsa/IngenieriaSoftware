package com.example.proyectoveci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initActivity()
    }

    private fun initActivity(){
        val useremail = LoginClass.useremail
        val tvUserProfile = findViewById<TextView>(R.id.tvUserProfile)
        tvUserProfile.text = LoginClass.useremail
    }
}