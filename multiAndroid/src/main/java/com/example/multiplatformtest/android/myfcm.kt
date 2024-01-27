package com.example.multiplatformtest.android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.remoteMessage

class myfcm : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("newtoken", "new token : $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("newtoken", "from : ${message.from}")
        Log.d("newtoken", "msg notification :" + message.data)

        message.data.let {
            showNotification(it)
        }
    }

    private fun showNotification(data: Map<String, String>) {
        val channelId = "default_channel_id"
        val channelName = "Default Channel"
        val notificationId = 1

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.profile)
            .setContentTitle(data["title"] ?: "Default Title")
            .setContentText(data["body"] ?: "Default Body")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Default Channel"
                enableLights(true)
                lightColor = Color.BLUE
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Show the notification
        notificationManager.notify(notificationId, notificationBuilder.build())

    }
}
