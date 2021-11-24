package com.suleimanzhukov.movieguide.framework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.framework.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_de_fragmento, MainFragment.newInstance())
        }
    }
}