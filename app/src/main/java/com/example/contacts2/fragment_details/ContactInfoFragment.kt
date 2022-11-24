package com.example.contacts2.fragment_details

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.contacts2.databinding.FragmentContactInfoBinding
import com.example.contacts2.main.Contact
import com.example.contacts2.main.navigator


private const val KEY_CONTACT = "CONTACT"

class ContactInfoFragment() : Fragment() {

    private var binding: FragmentContactInfoBinding? = null
    private var vm: ContactInfoViewModel? = null
    private lateinit var contact: Contact
    private lateinit var onContactSaved: (Contact)->Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactInfoBinding.inflate(inflater, container, false)

        vm = ViewModelProvider(
            this,
        )[ContactInfoViewModel::class.java]

        saveOrUpdateContact()
        init()
        return binding!!.root
    }

    private fun init() {
        arguments?.let {
            contact = it.getParcelable(KEY_CONTACT)!!
        }
        binding!!.etFirstNameContact.setText( contact.firstName)
        binding!!.etLastNameContact.setText ( contact.lastName)
        binding!!.etPhoneNumberContact.setText (contact.phoneNumber)

    }

    private fun saveOrUpdateContact() {
        binding!!.btnSave.setOnClickListener {
            contact.lastName = binding!!.etLastNameContact.text.toString()
            contact.firstName = binding!!.etFirstNameContact.text.toString()
            contact.phoneNumber = binding!!.etPhoneNumberContact.text.toString()
            onContactSaved(contact)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(contact: Contact?, onContactSaved: (Contact)->Unit) =
            ContactInfoFragment().apply {
                this.onContactSaved = onContactSaved
                arguments = Bundle().apply {
                    putParcelable(KEY_CONTACT, contact)
                }
            }
    }


}