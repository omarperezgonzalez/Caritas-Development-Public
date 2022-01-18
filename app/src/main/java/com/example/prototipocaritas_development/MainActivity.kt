package com.example.prototipocaritas_development

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipocaritas_development.utils.FragmentAdapter
import android.view.Window
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.prototipocaritas_development.controller.general.FragmentOnlineLogin
import com.example.prototipocaritas_development.controller.general.FragmentRegisterLocal
import com.example.prototipocaritas_development.controller.general.FragmentWelcomeScreen
import com.example.prototipocaritas_development.utils.LoginStatus
import com.example.prototipocaritas_development.utils.PreferenceManager

class MainActivity : AppCompatActivity() {
    //Space where fragments are displayed
    //lateinit var pager2: ViewPager2
    lateinit var pageAdapter: FragmentAdapter
    lateinit var fragmentTransaction: FragmentTransaction

    //Login variables
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        //Removes head title
        supportActionBar?.hide()

        pageAdapter = FragmentAdapter(supportFragmentManager, lifecycle)

        //Load preferences
        preferenceManager = PreferenceManager(this)
        preferenceManager.initializePreferences()

        launchActivity(preferenceManager.loadLoginPreferences())
    }

    private fun launchActivity(activity: LoginStatus){
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, FragmentWelcomeScreen())
        fragmentTransaction.addToBackStack("tag")
        when(activity){
            LoginStatus.ONLINE_ADMIN -> {
                fragmentTransaction.replace(R.id.fragment_container, FragmentOnlineLogin()).commit()
            }
            LoginStatus.ONLINE_DONOR -> {
                fragmentTransaction.replace(R.id.fragment_container, FragmentOnlineLogin()).commit()
            }
            LoginStatus.OFFLINE_LOCAL -> {
                intent = Intent(this, ActivityLocal::class.java)
                startActivity(intent)
            }
            LoginStatus.LOGGED_OUT -> {
                fragmentTransaction.replace(R.id.fragment_container, FragmentWelcomeScreen())
                    .commit()
            }
            LoginStatus.DEBUGGING -> {
                intent = Intent(this, ActivityDonnor::class.java)
                startActivity(intent)
            }

        }
    }
}