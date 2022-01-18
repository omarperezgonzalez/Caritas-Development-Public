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
import android.widget.Toast
import com.example.prototipocaritas_development.MainActivity
import com.example.prototipocaritas_development.R
import com.example.prototipocaritas_development.utils.PreferenceManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FragmentOnlineSettings : Fragment() {

    lateinit var showUsername : TextView
    lateinit var showEmail : TextView
    lateinit var showAccess : TextView
    lateinit var btnChangeUsername : Button
    lateinit var btnLogout : Button
    lateinit var inputNewUsername : EditText
    lateinit var preferenceManager: PreferenceManager
    lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online_donor_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = PreferenceManager(context)
        preferenceManager.initializePreferences()

        getUIObject(view)
        showUsername.text = preferenceManager.getUsernameLocal()
        showEmail.text = preferenceManager.getEmail()
        showAccess.text = preferenceManager.getAccess()
        assignClickListeners()
    }

    private fun getUIObject(view: View){
        showUsername = view.findViewById(R.id.vt_DonorShowUsername)
        showEmail = view.findViewById(R.id.vt_DonorShowEmail)
        showAccess = view.findViewById(R.id.vt_DonorAccess)
        btnChangeUsername = view.findViewById(R.id.btn_DonorChangeUsername)
        btnLogout = view.findViewById(R.id.btn_DonorLogout)
        inputNewUsername = view.findViewById(R.id.et_DonorChangeUsername)
    }

    private fun assignClickListeners(){
        btnChangeUsername.setOnClickListener(){
            setNewName()
        }

        btnLogout.setOnClickListener(){
            preferenceManager.resetData()
            intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setNewName(){
        if(inputNewUsername.text.toString().isNotEmpty()){
            preferenceManager.setUsername(inputNewUsername.text.toString())
            preferenceManager.saveOnlineUser(preferenceManager.getEmail())
            showSuccessDialog()
            showUsername.text = preferenceManager.getUsernameLocal()
            inputNewUsername.setText("")
        }
        else{
            Toast.makeText(
                this.requireContext(),
                "Escribe un usuario",
                Toast.LENGTH_SHORT)
                .show()
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