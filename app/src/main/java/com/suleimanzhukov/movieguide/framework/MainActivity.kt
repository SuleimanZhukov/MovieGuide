package com.suleimanzhukov.movieguide.framework

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.framework.ui.main.MainFragment
import com.suleimanzhukov.movieguide.framework.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        requestPermission()

        searchFragment()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_de_fragmento, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }

    private fun searchFragment() {
        searchView = findViewById(R.id.search_view)
        searchView.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_de_fragmento, SearchFragment.newInstance())
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    fun checkPermission() =
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED

    fun requestPermission() {
        val permissionToRequest = mutableListOf<String>()
        if (!checkPermission()) {
            permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(), 0)
        }
    }
}