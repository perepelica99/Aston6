package com.example.contacts2.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Contact(
    var firstName: String?,
    var lastName: String?,
    var phoneNumber: String?,
    val id: Int?
) : Parcelable