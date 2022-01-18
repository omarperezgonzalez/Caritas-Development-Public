package com.example.prototipocaritas_development.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context?) {
    private val newContext : Context? = context
    private lateinit var loginPreferences: SharedPreferences
    private var username: String = ""
    private var detailEventTxt: String = ""

    private val keyLogin = "loginStatus"
    private val keyUsername = "loginUsername"
    private val keyToken = "loginToken"
    private val keyEmail = "loginEmail"
    private val keyDetailEvent = "detailEvent"

    //Set up
    fun initializePreferences(){
        if (newContext != null) {
            loginPreferences = newContext.getSharedPreferences("current_Login", Context.MODE_PRIVATE)
        }
    }

    fun setUsername(newUsername: String){
        username = newUsername
    }

    //Offline



    fun getUsernameLocal() : String{
        username = loginPreferences.getString(keyUsername, "USERNAME NOT FOUND").toString()
        return  username
    }




    fun saveLocalUser(){
        val editor = loginPreferences.edit()
        editor.putInt(keyLogin, 3)
        editor.putString(keyUsername, username)
        editor.apply()
    }

    //Online
    fun saveOnlineUser(email:String){
        val editor = loginPreferences.edit()
        editor.putString(keyEmail, email)
        editor.putString(keyUsername, username)
        editor.putInt(keyLogin, 2)
        editor.apply()
    }

    fun getEmail() : String{
        return loginPreferences.getString(keyEmail, "EMAIL NOT FOUND").toString()
    }

    fun isLoggedIn() : Boolean {
        if(loginPreferences.getInt(keyLogin, 0) != 0){
            return true
        }
        return false
    }

    //Management

    fun resetData(){
        val editor = loginPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun loadLoginPreferences(): LoginStatus{
        //Loads shared preferences.
        //Defaults to 0 to ask for login, otherwise it searches for the last status of log in
        var loginStatus = when(loginPreferences.getInt(keyLogin, 0)){
            //0 --> REQUIRE LOGIN
            //1 --> ONLINE ADMIN
            //2 --> ONLINE DONOR
            //3 --> OFFLINE ACCOUNT
            //ELSE --> REQUIRE LOGIN
            0 -> LoginStatus.LOGGED_OUT
            1 -> LoginStatus.ONLINE_ADMIN
            2 -> LoginStatus.ONLINE_DONOR
            3 -> LoginStatus.OFFLINE_LOCAL
            else -> LoginStatus.LOGGED_OUT
        }

        return loginStatus
    }

    fun getAccess(): String {
        var loginAccess : String = when(loginPreferences.getInt(keyLogin, 0)){
            0 -> "Sesión cerrada"
            1 -> "Administrador"
            2 -> "Donador"
            3 -> "Local"
            else -> "Sesion cerrada"
        }

        return loginAccess
    }

    fun saveDetailEvent(texto: String){
        val editor = loginPreferences.edit()
        editor.putString(keyDetailEvent, texto)
        editor.commit()
    }

    fun getDetailEvent() : String{
        return loginPreferences.getString(keyDetailEvent, "EVENT NOT FOUND").toString()
    }




//    //It is used to save the last login, later we can add details like username and a token for login (or password) to save it and then load it.
//    //For now it is used for debugging and testing
//    private fun saveLoginPreference(currentLogin : LoginStatus){
//        val editor = loginPreferences.edit()
//        val intLoginStatus = when(currentLogin){
//            LoginStatus.LOGGED_OUT -> 0
//            LoginStatus.ONLINE_ADMIN -> 1
//            LoginStatus.ONLINE_DONOR -> 2
//            LoginStatus.OFFLINE_LOCAL -> 3
//            else -> 0
//        }
//        editor.putInt(keyLogin, intLoginStatus)
//        editor.apply()
//    }
}