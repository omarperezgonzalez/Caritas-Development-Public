package com.example.prototipocaritas_development

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipocaritas_development.utils.FragmentAdapter
import com.example.prototipocaritas_development.utils.FragmentAdapterLocal
import com.google.android.material.tabs.TabLayout

class ActivityLocal : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var pager2: ViewPager2
    lateinit var pageAdapter: FragmentAdapterLocal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_local)

        //Removes head title
        supportActionBar?.hide()

        tabLayout = findViewById(R.id.mainTabLayout)
        pager2 = findViewById(R.id.localViewPager)
        pageAdapter = FragmentAdapterLocal(supportFragmentManager, lifecycle)
        pager2.adapter = pageAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager2.currentItem = tab?.position ?: 0
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val tabToShow = tabLayout.getTabAt(position)
                tabLayout.selectTab(tabToShow)
            }
        })
    }

}