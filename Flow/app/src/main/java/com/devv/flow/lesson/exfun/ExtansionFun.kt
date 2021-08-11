package com.devv.flow.lesson.exfun

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun EditText.textChangeFlow(): Flow<String> {
    return callbackFlow<String> {
        val textTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySendBlocking(s?.toString().orEmpty())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        this@textChangeFlow.addTextChangedListener(textTextChangedListener)
        awaitClose {
            Log.d("TAGG", "awaitClose")
            this@textChangeFlow.removeTextChangedListener(textTextChangedListener)
        }
    }
}

fun CheckBox.checkboxChangesFlow(): Flow<Boolean> {
    return callbackFlow {
        val checkedChangeList = CompoundButton.OnCheckedChangeListener { _, isChecked ->
            trySendBlocking(isChecked)
        }
        setOnCheckedChangeListener(checkedChangeList)
        awaitClose {
            setOnCheckedChangeListener(null)
        }
    }
}