package com.example.prototipocaritas_development.controller.general

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.example.prototipocaritas_development.MainActivity
import com.example.prototipocaritas_development.R
import com.example.prototipocaritas_development.utils.PreferenceManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class FragmentLocalSettings : Fragment() {
    private lateinit var intent : Intent
    lateinit var displayName : TextView
    lateinit var changeUsername : Button
    lateinit var btnLogout : Button
    lateinit var inputNewUsername: EditText
    lateinit var preferenceManager: PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {


            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__local__settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inizializa las preferencias [No cambiar]
        //Primero declarar la variable, enviar el contexto del fragmento y finalmente llamar la funcion de inizializar para que funcione
        //No es lo mejor pero funciona
        preferenceManager = PreferenceManager(context)
        preferenceManager.initializePreferences()


        getUIObjects(view)
        assignClickListeners()
        displayName.text = preferenceManager.getUsernameLocal()

    }

    private fun getUIObjects(view: View){
        displayName = view.findViewById(R.id.tv_currentUser)
        changeUsername = view.findViewById(R.id.btn_LocalChangeUsername)
        btnLogout = view.findViewById(R.id.btn_localUserLogout)
        inputNewUsername = view.findViewById(R.id.et_changeUserInput)
    }

    private fun assignClickListeners(){
        changeUsername.setOnClickListener(){
            preferenceManager.resetData()
        }

        btnLogout.setOnClickListener(){
            preferenceManager.resetData()
            intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }

        changeUsername.setOnClickListener(){
            preferenceManager.setUsername(inputNewUsername.text.toString())
            preferenceManager.saveLocalUser()
            displayName.text = preferenceManager.getUsernameLocal()
            showSuccessDialog()
            inputNewUsername.setText("")
        }
    }


    private fun showSuccessDialog(){
        var message : String = "Tu usuario ha sido guardado " + preferenceManager.getUsernameLocal()
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Cambio de nombre exitoso")
                .setMessage(message)
                .setPositiveButton("Aceptar", DialogInterface.OnClickListener { _, _ ->  })
                .show()
        }
    }
}