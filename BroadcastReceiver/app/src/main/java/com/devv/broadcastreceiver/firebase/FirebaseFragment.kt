package com.devv.broadcastreceiver.firebase

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.broadcastreceiver.R
import com.devv.broadcastreceiver.databinding.FirebaseFragmentBinding
import com.google.firebase.iid.FirebaseInstanceIdReceiver
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC = "/topics/myTopic"

class FirebaseFragment : Fragment(R.layout.firebase_fragment) {

    private val TAG = "TAG"

    private val binding: FirebaseFragmentBinding by viewBinding(FirebaseFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        binding.getTokenButton.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val message = binding.editMessage.text.toString()
            if (title.isNotEmpty() && message.isNotEmpty()) {
                PushNotification(
                    NotificationData(title, message),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }
    }

    private fun sendNotification(notification: PushNotification) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstancs.api.postNotification(notification)
                if (response.isSuccessful) {
                    Log.d(TAG, "Response: ${Gson().toJson(response)}")
                } else {
                    Log.e(TAG, response.errorBody().toString())
                }

            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }

//    private fun getToken() {
//        lifecycleScope.launch {
//            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//                // Get new FCM registration token
//                val token = task.result
//                // Log and toast
//                Log.d(TAG, "token = $token")
//            })
//        }
//    }

//    private suspend fun getTokensu(): String? = suspendCoroutine { continuation ->
//        FirebaseMessaging.getInstance().token
//            .addOnSuccessListener { token ->
//                continuation.resume(token)
//            }
//            .addOnFailureListener { ex ->
//                continuation.resume(null)
//            }
//            .addOnCanceledListener {
//                continuation.resume(null)
//            }
//    }
}