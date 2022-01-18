package com.example.prototipocaritas_development.donorsUtils

import android.os.Bundle
//import androidx.fragment.app.Fragment
import androidx.fragment.app.Fragment
import com.example.prototipocaritas_development.R

import android.view.ViewGroup

import android.view.LayoutInflater

import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import com.example.prototipocaritas_development.utils.PreferenceManager


class eventFragment : Fragment() {

    companion object Items{
        lateinit var titleEvent : TextView
        lateinit var detailEvent : WebView
    }


    lateinit var preferenceManager: PreferenceManager
    lateinit var btnReturn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event, container, false)
//        return inflater.inflate(com.example.prototipocaritas_development.R.layout.fragment_event, RSSFragment(), false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = PreferenceManager(context)
        preferenceManager.initializePreferences()

        getUIObject(view)
        assignClickListener()
        titleEvent.text = preferenceManager.getDetailEvent()
    }

    private fun getUIObject(view: View){
        titleEvent = view.findViewById(R.id.onlineDetailEventTitle)
        detailEvent = view.findViewById(R.id.onlineDetailEventDescription)
        btnReturn = view.findViewById(R.id.returnButtonRss)


    }

    private fun assignClickListener(){
        btnReturn.setOnClickListener(){
            MyItemRecyclerViewAdapter.Fragments.rssPager.visibility = View.VISIBLE
            MyItemRecyclerViewAdapter.Fragments.pager2.visibility = View.INVISIBLE
        }
    }
}