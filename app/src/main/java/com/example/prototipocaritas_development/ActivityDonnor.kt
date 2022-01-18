package com.example.prototipocaritas_development

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.prototipocaritas_development.controller.general.FragmentLocalSettings
import com.example.prototipocaritas_development.controller.general.getIdFromSession
import com.example.prototipocaritas_development.donorsUtils.configureFragment
import com.example.prototipocaritas_development.donorsUtils.eventFragment
import com.example.prototipocaritas_development.utils.DonnorDataStruct
import com.example.prototipocaritas_development.utils.FragmentAdapter
import com.example.prototipocaritas_development.utils.FragmentAdapterDonnor
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

//ArrayList initializer, must be outside of class (not depending of it)
//DonnorDataStruct is in another file, it is a class that acts like a  C++ struct
public var dates : ArrayList<DonnorDataStruct> = ArrayList()

class ActivityDonnor : AppCompatActivity() {

    private lateinit var bottomNavBar : TabLayout
    lateinit var pager2: ViewPager2
    lateinit var pageAdapter: FragmentAdapterDonnor

    private val calendarFragment = com.example.prototipocaritas_development.donorsUtils.calendarFragment()
    //Cambiado temporalmente por uno de debugging
    private val confFragment = FragmentLocalSettings()
    private val eventFragment = eventFragment()

    lateinit var connect : Connection
    val url : String = "jdbc:jtds:sqlserver://tc2007b.database.windows.net:1433;DatabaseName=Caritas;user=tc2007b@tc2007b;password=caritasReto_Chazam!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=require"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDates()

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_donnor)
        //Removes head title
        supportActionBar?.hide()

        bottomNavBar = findViewById(R.id.tabMainLayout)
        pager2= findViewById(R.id.viewPager2)

        pageAdapter= FragmentAdapterDonnor(supportFragmentManager, lifecycle)
        pager2.adapter=pageAdapter



        bottomNavBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager2.currentItem=tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val tabtoShow=bottomNavBar.getTabAt(position)
                bottomNavBar.selectTab(tabtoShow)
            }
        })
    }

    @SuppressLint("NewApi")
    private fun databaseFunc() : Connection {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        lateinit var connection : Connection

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connection = DriverManager.getConnection(url)
        }
        catch (ex : Exception){
            Log.e("Error ", ex.localizedMessage)
        }
        return connection
    }

    private fun getDates(){
        val idSession = getIdFromSession()
        val datesQuery:String = "SELECT fecha_pago, b.importe  FROM Bitacora AS b\n" +
                "\tINNER JOIN\n" +
                "\tDonativos as d\n" +
                "\tON b.id_donativo = d.id_donativo\n" +
                "\tINNER JOIN\n" +
                "\tDonadores as a\n" +
                "\tON d.id_donante = a.id_donante\n" +
                "\tWHERE a.id_donante = "+idSession.toString()


        try{
            connect = databaseFunc()
            if(connect != null){
                val st : Statement = connect.createStatement()
                val rs : ResultSet = st.executeQuery(datesQuery)
                dates.clear()
                while(rs.next()){
                    val payDate= rs.getDate(1)
                    val payQty= rs.getDouble(2)
                    dates.add(DonnorDataStruct(payDate, "En línea", payQty))
                }
            } else{
                Log.e("Check connection", "Check Connection")
            }
        }
        catch(ex : Exception){
            Log.e("Error ", ex.localizedMessage)
        }
    }

    //Sets the dates **(This will change when database is ready)**
    /*private fun datesCreation(){
        for(item in 0 until 50)
            dates.add(DonnorDataStruct(
                Date(Random.nextInt(120,122), Random.nextInt(1, 12), Random.nextInt(1,28)),
                "BBVA",
                Random.nextDouble(100.0, 1000.0)))
    }*/
}

//This must be outside of "class" 'cause we need to import it to another files(get value func)
fun getArrayDates() : ArrayList<DonnorDataStruct>{
    return dates
}