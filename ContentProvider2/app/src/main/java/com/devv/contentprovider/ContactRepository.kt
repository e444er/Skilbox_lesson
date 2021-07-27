package com.devv.contentprovider

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class ContactRepository(private val context: Context) {

    suspend fun saveContact(name: String, number: String) = withContext(Dispatchers.IO) {
        val contactId = saveRawContact()
        saveContactName(contactId, name)
        saveContactNumber(contactId, number)
    }

    private fun saveRawContact(): Long {
        val uri = context.contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, ContentValues())
        return uri?.lastPathSegment?.toLongOrNull() ?: error("Not Number")
    }

    private fun saveContactName(contactId: Long, name: String) {
        val conVelos = ContentValues().apply {
            put(ContactsContract.Data.RAW_CONTACT_ID, contactId)
            put(ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
        }
        context.contentResolver.insert(ContactsContract.Data.CONTENT_URI, conVelos)
    }

    private fun saveContactNumber(contactId: Long, number: String) {
        val conVelos = ContentValues().apply {
            put(ContactsContract.Data.RAW_CONTACT_ID, contactId)
            put(ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            put(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
        }
        context.contentResolver.insert(ContactsContract.Data.CONTENT_URI, conVelos)
    }

    suspend fun getAllContacts(): List<Contact> = withContext(Dispatchers.IO) {
        context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null,
        )?.use {
            getFromCursor(it)
        }.orEmpty()
    }

    private fun getFromCursor(cursor: Cursor): List<Contact> {
        if (cursor.moveToFirst().not()) return emptyList()
        val list = mutableListOf<Contact>()
        do {
            val numberIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
            val name = cursor.getString(numberIndex).orEmpty()

            val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val id = cursor.getLong(idIndex)
            list.add(Contact(id = id, name = name, number = getNumberContact(id)))
        } while (cursor.moveToNext())
        return list
    }

    private fun getNumberContact(contactId: Long): List<String> {
        return context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} =?",
            arrayOf(contactId.toString()),
            null,
        )?.use {
            getPhoneCursor(it)
        }.orEmpty()
    }

    private fun getPhoneCursor(cursor: Cursor): List<String> {
        if (cursor.moveToFirst().not()) return emptyList()
        val list = mutableListOf<String>()
        do {
            val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val number = cursor.getString(numberIndex)

            list.add(number)
        } while (cursor.moveToNext())
        return list
    }
}