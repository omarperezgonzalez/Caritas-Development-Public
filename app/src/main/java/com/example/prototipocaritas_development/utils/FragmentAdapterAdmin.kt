package com.example.prototipocaritas_development.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.prototipocaritas_development.controller.general.FragmentOnlineSettings
import com.example.prototipocaritas_development.donorsUtils.RSSFragment

class FragmentAdapterAdmin (fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 3;
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> RSSFragment()
            1 -> FragmentOnlineSettings()
            else -> FragmentOnlineSettings();
        }
    }
}