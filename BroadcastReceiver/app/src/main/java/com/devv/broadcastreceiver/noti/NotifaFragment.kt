package com.devv.broadcastreceiver.noti

import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.devv.broadcastreceiver.R
import com.devv.broadcastreceiver.databinding.NotiFragmentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotifaFragment : Fragment(R.layout.noti_fragment) {
    private val binding: NotiFragmentBinding by viewBinding(NotiFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            showNotifi()
        }
        binding.button2.setOnClickListener {
            showMessage()
        }
        binding.button3.setOnClickListener {
            showNew()
        }
        binding.button4.setOnClickListener {
            group()
        }
        binding.button5.setOnClickListener {
            progres()
        }
    }

    //do 8
    private fun showNotifi() {
        val notif = NotificationCompat.Builder(requireContext(),
            "channel_id")
            .setContentTitle("my noti")
            .setContentText("test time ${System.currentTimeMillis()}")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setVibrate(longArrayOf(100, 200, 500, 500))
            .setContentText("test test")
            .setSmallIcon(R.drawable.ic_notifications_24)
            .build()

        NotificationManagerCompat.from(requireContext())
            .notify(SIMPLE, notif)
    }

    private fun showNew() {
        val notif = NotificationCompat.Builder(requireContext(),
            NotiChannel.NEWS_CHANNAL_ID)
            .setContentTitle("my update")
            .setContentText("test time update ${System.currentTimeMillis()}")
            .setSmallIcon(R.drawable.ic_message_24)
            .build()

        NotificationManagerCompat.from(requireContext())
            .notify(SIMPLEN, notif)
    }

    private fun showMessage() {
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.data1)

        val notif = NotificationCompat.Builder(requireContext(),
            NotiChannel.MESSAGE_CHANNAL_ID)
            .setContentTitle("my noti")
            .setContentText("test time ${System.currentTimeMillis()}")
            .setSmallIcon(R.drawable.ic_message_24)
            .setLargeIcon(largeIcon)
            .build()

        NotificationManagerCompat.from(requireContext())
            .notify(SIMPLEE, notif)
    }

    //background thred
    private fun loadGlide(url: String) {
        val bitmapp = Glide.with(this)
            .asBitmap()
            .load(url)
            .submit()
            .get()
    }

    private fun group() {
        val message = 3
        val groupId = "message group"
        (0 until message).forEach { messageIndex ->
            val messageNumber = messageIndex + 1
            val notif = NotificationCompat.Builder(requireContext(),
                NotiChannel.MESSAGE_CHANNAL_ID)
                .setContentTitle("my noti ${messageNumber}")
                .setContentText("test time ${System.currentTimeMillis()}")
                .setSmallIcon(R.drawable.ic_message_24)
                .setGroup(groupId)
                .build()

            NotificationManagerCompat.from(requireContext())
                .notify(messageNumber, notif)

        }
        val summaryNotification = NotificationCompat.Builder(requireContext(),
            NotiChannel.MESSAGE_CHANNAL_ID)
            .setContentTitle("Summary")
            .setContentText("test time ${System.currentTimeMillis()}")
            .setSmallIcon(R.drawable.ic_message_24)
            .setGroup(groupId)
            .setGroupSummary(true)
            .build()
        NotificationManagerCompat.from(requireContext())
            .notify(SUMMARY, summaryNotification)
    }


    private fun progres() {
        val notiBuilder = NotificationCompat.Builder(requireContext(),
            NotiChannel.NEWS_CHANNAL_ID)
            .setContentTitle("Progress")
            .setContentText("test Download ${System.currentTimeMillis()}")
            .setSmallIcon(R.drawable.ic_message_24)
            .setPriority(NotificationCompat.PRIORITY_LOW)
        val maxPragress = 10
        lifecycleScope.launch {
            (0 until maxPragress).forEach { progress ->
                val notif = notiBuilder
                    .setProgress(maxPragress, progress, false)
                    .build()

                NotificationManagerCompat.from(requireContext())
                    .notify(PRO, notif)

                delay(500)
            }
            val final  = notiBuilder
                .setContentText("Final Finish")
                .setProgress(0, 0, false)
                .build()

            NotificationManagerCompat.from(requireContext())
                .notify(PRO, final)
            delay(500)
            NotificationManagerCompat.from(requireContext())
                .cancel(PRO)
        }
    }

    companion object {
        private const val SIMPLE = 23432
        private const val SIMPLEE = 423
        private const val SIMPLEN = 325
        private const val SUMMARY = 7687
        private const val PRO = 423432
    }
}