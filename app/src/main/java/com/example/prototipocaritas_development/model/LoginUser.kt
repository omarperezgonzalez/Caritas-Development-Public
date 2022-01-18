package com.example.prototipocaritas_development.model

import android.content.SharedPreferences
import com.example.prototipocaritas_development.utils.LoginStatus

class LoginUser {
    //Login variables
    private lateinit var loginPreferences: SharedPreferences
    private val keyLogin = "loginStatus"
    private val keyUsername = "loginUsername"
    private val keyToken = "loginToken"

    val currentLoginStatus: LoginStatus = LoginStatus.LOGGED_OUT
    private var username: String = ""
    private var token: String = ""

    fun getCurrentUsername(): String{
        return username
    }

    //Log in all users
    fun loginUser(loginType: LoginStatus){
        when (loginType) {
            LoginStatus.ONLINE_ADMIN -> {
                username = "ADMIN USER"
            }
            LoginStatus.ONLINE_DONOR -> {
                username = "DONOR USER"
            }
            LoginStatus.OFFLINE_LOCAL -> {
                username = loginPreferences.getString(keyUsername, "INVALID USER").toString()
            }
        }
    }

    fun registerLocalUser(loginType: LoginStatus, newUsername: String){
        val editor = loginPreferences.edit()
        editor.putString(keyUsername, newUsername)
        editor.putInt(keyLogin, 3)
        editor.commit()
    }
}