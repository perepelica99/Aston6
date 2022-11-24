package com.example.contacts2.main

import androidx.fragment.app.Fragment

fun Fragment.navigator(): ContactsNavigator {
    return requireActivity() as ContactsNavigator
}

interface ContactsNavigator {

    fun launchInfoFragment(contact: Contact, onContactSaved: (Contact)->Unit)



    fun showContactsFragment()

}