package com.example.prototipocaritas_development.controller.general

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.prototipocaritas_development.ActivityLocal
import com.example.prototipocaritas_development.R
import com.example.prototipocaritas_development.utils.PreferenceManager

class FragmentRegisterLocal : Fragment() {
    lateinit var usernameInput: EditText
    lateinit var btnConfirmUser: Button
    lateinit var preferenceManager: PreferenceManager
    lateinit var getSharedPreferenceManager: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment__register_local, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager = PreferenceManager(context)
        preferenceManager.initializePreferences()
        getUIObjects(view)
        assignClickListeners()

    }

    private fun getUIObjects(view: View){
        usernameInput = view.findViewById(R.id.et_inputUsername)
        btnConfirmUser = view.findViewById(R.id.btn_LocalRegisterUser)
    }

    private fun assignClickListeners(){
        btnConfirmUser.setOnClickListener(){
            if(usernameInput.text.toString().isNotEmpty()){
                val message = usernameInput.text.toString()
                var intent = Intent(activity, ActivityLocal::class.java)

                preferenceManager.setUsername(message)
                preferenceManager.saveLocalUser()

                startActivity(intent)
            }
            else{
                Toast.makeText(
                    this.requireContext(),
                    "Escribe un usuario",
                    Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }
}