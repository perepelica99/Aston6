package com.example.contacts2.fragment_details

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts2.main.Contact
import kotlinx.coroutines.launch
import java.lang.Exception

class ContactInfoViewModel(
) : ViewModel() {

    fun save(firstName: String?, lastName: String?, phone: String?, id: Int?) {

        try {
            viewModelScope.launch {
                val contact = Contact(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phone,
                    id = id
                )

            }
        } catch (e: Exception) {
        }
    }

    fun update(firstName: String?, lastName: String?, phone: String?, id: Int?) {
        val contact = Contact(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phone,
            id = id
        )
        try {
            viewModelScope.launch {

            }
        } catch (e: Exception) {
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(contact: Contact) =
            ContactInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_CONTACT, contact)
                }
            }

        private const val KEY_CONTACT = "CONTACT"
    }
}


