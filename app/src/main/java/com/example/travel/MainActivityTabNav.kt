package com.example.travel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityBottomNav : AppCompatActivity() {
    private val frame : RelativeLayout by lazy {
        findViewById(R.id.fragment)
    }
    private val bottomNavigationView: BottomNavigationView by lazy {
        findViewById(R.id.bottomNav)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(frame.id,StartFragment()).commit()

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.mainFragment -> {
                    replaceFragment(StartFragment())
                    true
                }
                R.id.routeFragment -> {
                    replaceFragment(RouteFragment())
                    true
                }
                R.id.mypageFragment -> {
                    replaceFragment(MypageFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
    }
}
