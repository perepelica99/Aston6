package com.example.contacts2.fragment_contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts2.*
import com.example.contacts2.adapter.ContactsListener
import com.example.contacts2.main.Contact
import com.example.contacts2.main.navigator
import java.lang.Exception
import java.util.*

class ContactsListViewModel() : ViewModel(), ContactsListener {
   private lateinit var listener:ContactsListener

    override fun onItemClick(contact: Contact) {
        listener.onItemClick(contact)
    }


    fun setOnContactClickListener(listener: ContactsListener){
        this.listener = listener
    }


    override fun onLongItemClick(id: Int?): Boolean {
        TODO("Not yet implemented")
    }


}