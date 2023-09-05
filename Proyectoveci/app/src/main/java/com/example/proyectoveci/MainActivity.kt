package com.example.proyectoveci

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.example.proyectoveci.LoginClass.Companion.useremail

class MainActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()
        initNavigationView()

    }

    private fun initToolBar(){
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.bar_title,
            R.string.navigation_drawer_close)

        drawer.addDrawerListener(toggle)

        toggle.syncState()
    }

    private fun initNavigationView(){
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var headerView: View = LayoutInflater.from(this).inflate(R.layout.nav_header_main, navigationView, false)
        navigationView.removeHeaderView(headerView)
        navigationView.addHeaderView(headerView)

        var tvUser: TextView = headerView.findViewById(R.id.tvUser)
        tvUser.text = useremail
    }



    fun closeSession(view:View){
        cierreSesion()
    }
    private fun cierreSesion(){
        useremail = ""
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,LoginClass::class.java))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_item_canasta -> callCanastaActivity()
            R.id.nav_item_signout -> cierreSesion()
            R.id.nav_item_premium -> callPremiumActivity()
            R.id.nav_item_clearpreferences -> callSettingsActivity()
        }

        return true
    }
    private fun callCanastaActivity(){
        val intent = Intent(this,CanastaActivity::class.java)
        startActivity(intent)
    }
    private fun callPremiumActivity(){
        val intent = Intent(this,PremiumActivity::class.java)
        startActivity(intent)
    }
    private fun callProfileActivity(){
        val intent = Intent(this,ProfileActivity::class.java)
        startActivity(intent)
    }
    private fun callSettingsActivity(){
        val intent = Intent(this,SettingsActivity::class.java)
        startActivity(intent)
    }

}