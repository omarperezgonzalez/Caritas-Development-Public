package com.example.prototipocaritas_development.donorsUtils

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prototipocaritas_development.R
import java.io.IOException
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [RSSFragment.OnListFragmentInteractionListener] interface.
 */
class RSSFragment : Fragment() {



    private var columnCount = 1
    private var listener: OnListFragmentInteractionListener? = null

    val RSS_FEED_LINK = "https://www.caritas.org.mx/feed/"

    var adapter: MyItemRecyclerViewAdapter? = null
    var rssItems = ArrayList<RssItem>()
    var listV : RecyclerView ?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        listV = view.findViewById(R.id.listV)

        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = MyItemRecyclerViewAdapter(rssItems, listener, activity)
        listV?.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        listV?.adapter = adapter

        val url = URL(RSS_FEED_LINK)
        RssFeedFetcher(this).execute(url)
    }

    fun updateRV(rssItemsL: List<RssItem>) {
        if (rssItemsL.isNotEmpty()) {
            rssItems.addAll(rssItemsL)
            adapter?.notifyDataSetChanged()
        }
    }

    class RssFeedFetcher(val context: RSSFragment) : AsyncTask<URL, Void, List<RssItem>>() {
        val reference = WeakReference(context)
        private var stream: InputStream? = null
        override fun doInBackground(vararg params: URL?): List<RssItem>? {
            val connect = params[0]?.openConnection() as HttpsURLConnection
            connect.readTimeout = 8000
            connect.connectTimeout = 8000
            connect.requestMethod = "GET"
            connect.connect()
            val responseCode: Int = connect.responseCode
            var rssItems: List<RssItem>? = null
            if (responseCode == 200) {
                stream = connect.inputStream
                try {
                    val parser = RssParser()
                    rssItems = parser.parse(stream!!)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return rssItems
        }
        override fun onPostExecute(result: List<RssItem>?) {
            super.onPostExecute(result)
            if (result != null && result.isNotEmpty()) {
                reference.get()?.updateRV(result)
            }
        }
    }
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: RssItem?)
    }
}