package com.suleimanzhukov.movieguide.framework

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.framework.ui.main.MainFragment
import com.suleimanzhukov.movieguide.framework.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private var submittedText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        requestPermission()

        searchView = findViewById(R.id.search_view)
        search()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_de_fragmento, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }

    private fun search() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                val bundle = Bundle().apply {
                    putString(SearchFragment.SEARCH_KEY, text)
                }
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container_de_fragmento, SearchFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()

                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return true
            }

        })
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