package com.example.prototipocaritas_development.controller.general

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.prototipocaritas_development.MainActivity
import com.example.prototipocaritas_development.R

class FragmentWelcomeScreen : Fragment() {
    //UI Elements
    lateinit var btnLocalUser: Button
    lateinit var btnOnlineUser: Button
    lateinit var fragmentTransaction: FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__welcome_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUIObjects(view)
        assignClickListeners()
    }

    private fun getUIObjects(view: View){
        btnLocalUser = view.findViewById(R.id.btnLocalLogin)
        btnOnlineUser = view.findViewById(R.id.btnOnlineLogin)
    }

    private fun assignClickListeners(){
        btnLocalUser.setOnClickListener(){
            fragmentTransaction = fragmentManager?.beginTransaction() ?:
            fragmentTransaction.add(R.id.fragment_container, FragmentWelcomeScreen())
            fragmentTransaction.addToBackStack("tag")
            fragmentTransaction.replace(R.id.fragment_container, FragmentRegisterLocal())
            fragmentTransaction.commit()
        }

        btnOnlineUser.setOnClickListener(){
            fragmentTransaction = fragmentManager?.beginTransaction() ?:
            fragmentTransaction.add(R.id.fragment_container, FragmentWelcomeScreen())
            fragmentTransaction.addToBackStack("tag")
            fragmentTransaction.replace(R.id.fragment_container, FragmentOnlineLogin())
            fragmentTransaction.commit()
        }
    }

}