package com.example.prototipocaritas_development.donorsUtils

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.prototipocaritas_development.R
import com.example.prototipocaritas_development.utils.DonnorDataStruct
import java.util.*
import com.example.prototipocaritas_development.getArrayDates

class CalendarDatesFragment : Fragment() {

    lateinit var textV : TextView
    lateinit var donnorDates : ArrayList<DonnorDataStruct>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_dates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        donnorDates= getArrayDates()
        getUIObject(view)
        textInit()
    }

    //Gets the id's from the object
    private fun getUIObject(view:View){
        textV=view.findViewById(R.id.textView3)
    }

    //Initializes text (Creates the view for the Android UI)
    private fun textInit(){
        textV.text="Donnor Payment amount and method\n\n"
        if(donnorDates.size == 0) textV.text="No fue posible cargar la base de datos"
        for(item in 0 until donnorDates.size){
            val stringQty = String.format("%.2f", donnorDates[item].payQty)
            textV.append("$" + stringQty + "\tvía\t" +
                    donnorDates[item].payMethod + "\n")
        }
    }
}