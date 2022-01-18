package com.example.prototipocaritas_development.donorsUtils

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.prototipocaritas_development.R
import com.example.prototipocaritas_development.utils.DonnorDataStruct
import java.util.*
import com.example.prototipocaritas_development.getArrayDates
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import java.text.SimpleDateFormat
import android.widget.Toast

import com.example.prototipocaritas_development.MainActivity
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener


//If this file is not working, maybe u dont have CompactCalendarView
//Check this URL for the instructions: https://github.com/SundeepK/CompactCalendarView
/*
    It just works adding: implementation 'com.github.sundeepk:compact-calendar-view:3.0.0'
    To the build.gradle(:app) file
*/
class calendarFragment : Fragment() {
    lateinit var webView : WebView
    lateinit var makeDonation : Button
    var isEnabled : Boolean = true
    lateinit var donnorDates : ArrayList<DonnorDataStruct>
    lateinit var calendarV : CompactCalendarView
    lateinit var toolbar : Toolbar

    private var dateFormatForMonth = SimpleDateFormat("MMMM- yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(isEnabled){
                    webView.visibility = View.INVISIBLE
                    webView.clearCache(true)
                    makeDonation.visibility = View.VISIBLE
                    isEnabled = false
                }
                else{
                    requireActivity().onBackPressed()
                }
            }

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUIObject(view)
        assignClickListener()
        isEnabled = false
        donnorDates= getArrayDates()
        calendarHighlight()

        calendarV.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                for(item in 0 until donnorDates.size){
                    if(dateClicked==donnorDates[item].payDay){
                        val stringQty = "Donation: $" + String.format("%.2f", donnorDates[item].payQty)
                        Toast.makeText(activity, stringQty, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                // Changes toolbar title on monthChange
                toolbar.title=dateFormatForMonth.format(firstDayOfNewMonth)
            }
        })
    }

    private fun getUIObject(view: View){
        webView = view.findViewById(R.id.webView_donations)
        makeDonation = view.findViewById(R.id.btn_makeNewDonation)
        calendarV = view.findViewById(R.id.calendarView)
        toolbar = view.findViewById(R.id.toolbar)
    }

    private fun assignClickListener(){
        makeDonation.setOnClickListener(){
            webView.visibility = View.VISIBLE
            webView.loadUrl("https://www.caritas.org.mx/donaciones-economicas/")
            webView.settings.javaScriptEnabled
            webView.webViewClient = WebViewClient()
            makeDonation.visibility = View.INVISIBLE
            isEnabled = true
        }
    }

    private fun calendarHighlight(){
        toolbar.title=null
        calendarV.setUseThreeLetterAbbreviation(true)
        toolbar.title=dateFormatForMonth.format(calendarV.firstDayOfCurrentMonth)
        calendarV.shouldDrawIndicatorsBelowSelectedDays(true)

        if(donnorDates.size == 0) makeDonation.text="No fue posible cargar la base de datos"
        for(item in 0 until donnorDates.size){
            val aux = Event(Color.BLACK, donnorDates[item].payDay.time)
            calendarV.addEvent(aux,false)
        }
        calendarV.invalidate()
    }

}