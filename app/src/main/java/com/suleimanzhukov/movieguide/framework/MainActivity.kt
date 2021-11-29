package com.suleimanzhukov.movieguide.framework

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.framework.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_de_fragmento, MainFragment.newInstance())
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