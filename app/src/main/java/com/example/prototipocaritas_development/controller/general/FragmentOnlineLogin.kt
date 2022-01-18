package com.example.prototipocaritas_development.controller.general

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentTransaction
import com.example.prototipocaritas_development.*
import com.example.prototipocaritas_development.utils.DonnorDataStruct
import com.example.prototipocaritas_development.utils.PreferenceManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.util.jar.Manifest

public var idLogIn : Int = 0

class FragmentOnlineLogin : Fragment() {
    private lateinit var intent : Intent
    lateinit var preferenceManager: PreferenceManager
    lateinit var inputEmail: EditText
    lateinit var inputPassword: EditText
    lateinit var saveUser: CheckBox
    lateinit var btnLogin: Button

    lateinit var connect : Connection
    lateinit var connection : Connection
    private val url : String = "jdbc:jtds:sqlserver://tc2007b.database.windows.net:1433;DatabaseName=Caritas;user=tc2007b@tc2007b;password=caritasReto_Chazam!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=require"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.INTERNET), PackageManager.PERMISSION_GRANTED)

        preferenceManager = PreferenceManager(context)
        preferenceManager.initializePreferences()

        getUIObjects(view)
        saveUser.isChecked = false
        btnLogin.isEnabled = false
        if(preferenceManager.isLoggedIn()){
            inputEmail.setText(preferenceManager.getEmail())
            btnLogin.isEnabled = isEmailValid(inputEmail.text.toString()) && inputPassword.text.isNotEmpty()
            saveUser.isChecked = true
        }
        checkListeners()
    }

    private fun getUIObjects(view: View){
        inputEmail = view.findViewById(R.id.et_userOnlineEmail)
        inputPassword = view.findViewById(R.id.et_userOnlinePassword)
        saveUser = view.findViewById(R.id.checkbox_rememberUsername)
        btnLogin = view.findViewById(R.id.btn_onlineUserLogin)
    }


    private fun checkListeners(){

        btnLogin.setOnClickListener(){

            when(signInUser()){
                0 -> {
                    showErrorDialog()
                }
                1 -> {
                    intent = Intent(activity, ActivityAdmin::class.java)
                    startActivity(intent)
                }
                2 -> {
                    intent = Intent(activity, ActivityDonnor::class.java)
                    startActivity(intent)
                }
            }
        }

        inputEmail.addTextChangedListener(){
            btnLogin.isEnabled = isEmailValid(inputEmail.text.toString()) && inputPassword.text.isNotEmpty()
        }

        inputPassword.addTextChangedListener(){
            btnLogin.isEnabled = isEmailValid(inputEmail.text.toString()) && inputPassword.text.isNotEmpty()
        }
    }

    private fun isEmailValid(email: CharSequence) : Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showErrorDialog(){
        var saveButton : Int
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Datos incorrectos")
                .setMessage("Intenta de nuevo")
                .setPositiveButton("Aceptar", DialogInterface.OnClickListener { _, _ -> saveButton = 1 })
                .show()
        }
    }

    @SuppressLint("NewApi")
    private fun databaseFunc() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connection = DriverManager.getConnection(url)
        }
        catch (ex : Exception){
            Log.e("Error ", ex.localizedMessage)
        }
    }

    public fun getDataFromDataBase(query : String) : String{
        var result = "ERROR"
        try{
            databaseFunc()
            connect = connection
            val st : Statement= connect.createStatement()
            val rs : ResultSet= st.executeQuery(query)

            while(rs.next()){
                result=rs.getString(1)
                return result
            }
        }
        catch(ex : Exception){
            Log.e("Error ", ex.localizedMessage)
        }
        return result
    }

    public fun signInUser() : Int {
        //Aqui verificamos con la base de datos el login del usuario

        //0 --> Usuario no encontrado
        //1 --> Admin
        //2 --> Donador

        val queryEmail : String = "SELECT email FROM Donadores WHERE email = '"+inputEmail.text.toString()+"'"
        val queryNombre : String = "SELECT nombre FROM Donadores WHERE email = '"+inputEmail.text.toString()+"'"
        val queryApellido : String = "SELECT a_paterno FROM Donadores WHERE email = '"+inputEmail.text.toString()+"'"
        val queryPassword : String = "SELECT password FROM Donadores WHERE password = '"+inputPassword.text.toString()+"'"

        val databaseEmail = getDataFromDataBase(queryEmail)
        val databasePassword = getDataFromDataBase(queryPassword)
        val databaseNombre = getDataFromDataBase(queryNombre)
        val databaseApellido = getDataFromDataBase(queryApellido)

        return if(inputEmail.text.toString() == databaseEmail && inputPassword.text.toString() == databasePassword){
            preferenceManager.setUsername(databaseNombre+" "+databaseApellido+" Donnor")
            if(saveUser.isChecked){
                preferenceManager.saveOnlineUser(inputEmail.text.toString())
            }
            val queryID : String = "SELECT id_donante FROM Donadores WHERE email = '$databaseEmail'"
            idLogIn= getDataFromDataBase(queryID).toInt()
            2
        } else {
            0
        }
    }
}
fun getIdFromSession() : Int{
    return idLogIn
}