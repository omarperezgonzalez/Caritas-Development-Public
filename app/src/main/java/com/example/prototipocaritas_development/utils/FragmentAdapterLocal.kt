package com.example.prototipocaritas_development.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.prototipocaritas_development.controller.general.FragmentLocalSettings
import com.example.prototipocaritas_development.donorsUtils.RSSFragment
import com.example.prototipocaritas_development.donorsUtils.eventFragment

class FragmentAdapterLocal (fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        //0 --> Welcome Screen
        return when(position){
            0 -> RSSFragment()
            1 -> FragmentLocalSettings()
            else -> FragmentLocalSettings();
        }
    }
}