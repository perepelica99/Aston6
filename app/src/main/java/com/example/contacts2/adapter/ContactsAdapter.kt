package com.example.contacts2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.contacts2.R
import com.example.contacts2.databinding.ItemContactBinding
import com.example.contacts2.fragment_contacts.ContactsListViewModel
import com.example.contacts2.main.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


interface ContactsListener {

    fun onItemClick(contact: Contact)

    fun onLongItemClick(id: Int?): Boolean
}

class ContactsAdapter(
    var listener: ContactsListViewModel
) : ListAdapter<Contact, ContactsViewHolder>(ContactsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)

        return ContactsViewHolder(itemView = itemView, listener = listener)
    }


    override fun onBindViewHolder(holderContacts: ContactsViewHolder, position: Int) {
        holderContacts.onBind(getItem(position))
    }

    private class ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {

        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}

class ContactsViewHolder(
    itemView: View,
    private val listener: ContactsListViewModel,
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemContactBinding.bind(itemView)

    fun onBind(contact: Contact) = with(binding) {
        firstNameItem.text = contact.firstName
        lastNameItem.text = contact.lastName
        phoneNumberItem.text = contact.phoneNumber

        CoroutineScope(context = Dispatchers.IO).launch {
            contactImage.load("https://picsum.photos/200/300?random=${(contact.id?.times(2))}") {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                transformations(CircleCropTransformation())
            }
        }

        card.setOnClickListener {
            listener.onItemClick(contact = contact)
        }

        card.setOnLongClickListener {
            listener.onLongItemClick(id = contact.id)
        }

    }
}