package com.example.contacts2.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.contacts2.R
import com.example.contacts2.databinding.ActivityMainBinding
import com.example.contacts2.fragment_contacts.ContactsFragment
import com.example.contacts2.fragment_details.ContactInfoFragment

class MainActivity :  AppCompatActivity(), ContactsNavigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFirstFragment()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



    override fun launchInfoFragment(
        contact: Contact?
    ) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                ContactInfoFragment.newInstance(
                    contact = contact,
                ),
                "SECOND_THREAD"
            ).addToBackStack("STACK")
            .commit()
    }

    override fun showContactsFragment() {
        Log.e("TAg", "ACTION")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ContactsFragment())
            .commit()
    }



    private fun addFirstFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ContactsFragment(), "MAIN_FRAGMENT")
            .addToBackStack("MAIN_FRAGMENT")
            .commit()
    }

}