package com.example.prototipocaritas_development.donorsUtils

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.prototipocaritas_development.R

class RssItem{



    var title = ""
    var link = ""
    var pubDate = ""
    var description = ""
    var category = ""
    var content = ""


    override fun toString(): String {
        return "RssItem(title='$title', link='$link', pubDate='$pubDate', description='$description', category='$category', content='$content')"
    }

    





}