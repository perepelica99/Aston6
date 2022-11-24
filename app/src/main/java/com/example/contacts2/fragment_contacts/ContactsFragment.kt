package com.example.contacts2.fragment_contacts

import android.os.Bundle
import android.view.*
import android.widget.AdapterView.OnItemClickListener
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts2.R
import com.example.contacts2.adapter.ContactsAdapter
import com.example.contacts2.adapter.ContactsListener
import com.example.contacts2.databinding.FragmentContactsBinding
import com.example.contacts2.main.Contact
import com.example.contacts2.main.navigator


class ContactsFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var contactList: MutableList<Contact>
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

        vm = ViewModelProvider(
            this,
        )[ContactsListViewModel::class.java]

        contactsAdapter = ContactsAdapter(listener = vm!!)

        contactList = mutableListOf<Contact>()
        for (i in 1..102){
            contactList.add(Contact(firstName = "firstName $i", lastName = "lastname $i", phoneNumber = "+7900$i", id = i))
        }
        contactsAdapter!!.submitList(contactList)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        vm!!.setOnContactClickListener(object : ContactsListener {
            override fun onItemClick(contact: Contact) {
                navigator().launchInfoFragment(contact) {
                    val index = contactList.indexOf(contact)
                    contactList[index] = contact
                    contactsAdapter!!.submitList(contactList)
                    contactsAdapter?.notifyDataSetChanged()
                }
            }

            override fun onLongItemClick(id: Int?): Boolean {
                TODO("Not yet implemented")
            }
        })



//        binding!!.addItem.setOnClickListener {
//            navigator().launchInfoFragment(
//                contact = null
//            )
//        }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? androidx.appcompat.widget.SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }
}


