package com.example.prototipocaritas_development.donorsUtils

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide.with
import com.example.prototipocaritas_development.ActivityDonnor
import com.example.prototipocaritas_development.R
import com.example.prototipocaritas_development.utils.FragmentDetailEvent
import com.example.prototipocaritas_development.utils.PreferenceManager
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

import android.webkit.WebView
import android.webkit.WebViewClient


class MyItemRecyclerViewAdapter (
    private val mValues: List<RssItem>,
    private val mListener: RSSFragment.OnListFragmentInteractionListener?,
    private val context : FragmentActivity?

) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    companion object Fragments{
        lateinit var pager2: ViewPager2
        lateinit var rssPager : ViewPager2
    }


    lateinit var pageAdapter : FragmentDetailEvent
    lateinit var preferenceManager: PreferenceManager
    var isEnabled : Boolean = false

    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
        //val item = v.tag as DummyItem
        // Notify the active callbacks interface (the activity, if the fragment is attached to
        // one) that an item has been selected.
        //mListener?.onListFragmentInteraction(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_rss_item, parent, false)

        val callback = ActivityDonnor().onBackPressedDispatcher.addCallback(RSSFragment(), object : OnBackPressedCallback(true){

            override fun handleOnBackPressed() {
                Log.e("Test", "Presione atras!")
                if(isEnabled){
                    pager2.visibility = View.INVISIBLE
                    rssPager.visibility = View.VISIBLE
                    isEnabled = false
                }
                else{
                    pager2.visibility = View.INVISIBLE
                    rssPager.visibility = View.VISIBLE
                    isEnabled = false
                    ActivityDonnor().onBackPressed()
                }
            }
        })

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (context != null) {
            preferenceManager = PreferenceManager(context)
            preferenceManager.initializePreferences()
            pageAdapter = FragmentDetailEvent(context.supportFragmentManager, context.lifecycle)
            getUIObjects(context)
            pager2.adapter = pageAdapter
        }

        val item = mValues[position]
        holder.titleTV?.text = item.title
        holder.pubDateTV?.text = item.pubDate
        holder.categoryTV?.text = item.category
        holder.btnEvent?.tag = position
        val link = getFeaturedImageLink(item.link)
        if(link != null) {
            context?.let {
                with(it)
                    .load(link)
                    .into(holder.featuredImg)
            }
        }





        holder.descriptionTV?.text = item.description
        // holder.contentTV?.text  = item.content

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }


        holder.btnEvent?.setOnClickListener(){
            // holder.btnEvent.tag.toString()
            val item = mValues[holder.btnEvent.tag.toString().toInt()]
            eventFragment.titleEvent.text = item.title
            eventFragment.detailEvent.loadData(item.content, "text/html; charset=utf-8", "UTF-8")
            eventFragment.detailEvent.settings.javaScriptEnabled
//            eventFragment.detailEvent.settings.forceDark.
            //eventFragment.detailEvent.setBackgroundColor(Color.TRANSPARENT)
//            eventFragment.detailEvent.
//            eventFragment.detailEvent.webViewClient = WebViewClient()



            pager2.visibility = View.VISIBLE
            rssPager.visibility = View.INVISIBLE
            pager2.currentItem = 0
            isEnabled = true
        }


    }

    private fun getUIObjects(context: FragmentActivity){
        when(preferenceManager.getAccess()){
            "Donador" -> {
                pager2 = context.findViewById(R.id.viewPagerDetailEvent)
                rssPager = context.findViewById(R.id.viewPager2)
                pager2.visibility = View.INVISIBLE
                rssPager.visibility = View.VISIBLE
            }
            else->{
                pager2 = context.findViewById(R.id.localViewPagerDetailEvent)
                rssPager = context.findViewById(R.id.localViewPager)
                pager2.visibility = View.INVISIBLE
                rssPager.visibility = View.VISIBLE
            }



        }

    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titleTV: TextView? = mView.findViewById(R.id.txtTitle)
        val pubDateTV: TextView? = mView.findViewById(R.id.txtPubdate)
        val featuredImg: ImageView = mView.findViewById(R.id.featuredImg)
        val descriptionTV: TextView? = mView.findViewById(R.id.txtDescription)
        val categoryTV: TextView? = mView.findViewById(R.id.txtCategory)
        val btnEvent : Button? = mView.findViewById(R.id.btnVerMas)
    }


    private fun getFeaturedImageLink(htmlText: String): String? {
        var result: String? = null

        val stringBuilder = StringBuilder()
        try {
            val doc: Document = Jsoup.parse(htmlText)
            val imgs: Elements = doc.select("img")
            for (img in imgs) {
                val src = img.attr("src")
                result = src

            }

        } catch (e: IOException) {

        }
        return result

    }

}