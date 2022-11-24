package com.example.contacts2.fragment_contacts

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts2.R
import com.example.contacts2.adapter.ContactsAdapter
import com.example.contacts2.databinding.FragmentContactsBinding
import com.example.contacts2.main.Contact
import com.example.contacts2.main.navigator


class ContactsFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var binding: FragmentContactsBinding? = null
    private var vm: ContactsListViewModel? = null
    private var contactsAdapter: ContactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().finish()
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        vm = ViewModelProvider(
            this,
        )[ContactsListViewModel::class.java]
        
        contactsAdapter = ContactsAdapter(listener = vm!!)

        val list = mutableListOf<Contact>()
        for (i in 1..102){
            list.add(Contact(firstName = "firstName $i", lastName = "lastname $i", phoneNumber = "+7900$i", id = i))
        }
        contactsAdapter!!.submitList(list)

        binding!!.addItem.setOnClickListener {
            navigator().launchInfoFragment(
                contact = null
            )
        }

        init()

        return binding!!.root
    }

    private fun init() {
        binding!!.rvFragmentContacts.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            hasFixedSize()
            adapter = this@ContactsFragment.contactsAdapter
        }
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
        binding!!.rvFragmentContacts.addItemDecoration(dividerItemDecoration)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

}


