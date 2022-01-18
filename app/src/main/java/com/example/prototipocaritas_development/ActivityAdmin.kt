package com.example.prototipocaritas_development

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipocaritas_development.utils.FragmentAdapter
import com.example.prototipocaritas_development.utils.FragmentAdapterAdmin
import com.example.prototipocaritas_development.utils.FragmentAdapterDonnor
import com.google.android.material.tabs.TabLayout

class ActivityAdmin : AppCompatActivity() {
    private lateinit var navBar : TabLayout
    lateinit var pager2: ViewPager2
    lateinit var pageAdapter: FragmentAdapterAdmin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_admin)
        supportActionBar?.hide()

        navBar = findViewById(R.id.tabAdminLayout)
        pager2 = findViewById(R.id.viewPagerAdmin)
        pageAdapter = FragmentAdapterAdmin(supportFragmentManager, lifecycle)
        pager2.adapter = pageAdapter

        navBar.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager2.currentItem = tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val tabToShow = navBar.getTabAt(position)
                navBar.selectTab(tabToShow)
            }
        })
    }
}