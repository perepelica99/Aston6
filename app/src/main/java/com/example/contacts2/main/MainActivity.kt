package com.example.contacts2.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.contacts2.R
import com.example.contacts2.databinding.ActivityMainBinding
import com.example.contacts2.fragment_contacts.ContactsFragment
import com.example.contacts2.fragment_details.ContactInfoFragment

class MainActivity :  AppCompatActivity(), ContactsNavigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ContactsFragment())
            .addToBackStack("STACK")
            .commit()

    }



    override fun launchInfoFragment(
        contact: Contact,
        onContactSaved: (Contact)->Unit
    ) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                ContactInfoFragment.newInstance(
                    contact = contact,
                    onContactSaved = {
                        onContactSaved(contact)
                        onBackPressed()
                    }
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
            .addToBackStack("STACK")
            .commit()
    }


    override fun onBackPressed() {

        val lastFragments: FragmentManager = supportFragmentManager

        if (lastFragments.backStackEntryCount == 2) {
            lastFragments.popBackStack()
        } else {
            finish()
        }
    }

}


