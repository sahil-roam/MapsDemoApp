package com.local.mapsdemoapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder

object NotificationsHelper {

    private const val ANDROID_CHANNEL_ID = "com.local.mapsdemoapp"
    const val NOTIFICATION_ID = 110

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun showNotification(context: Context): Notification {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val name: String = "mapsdemoapp"
        val channel = NotificationChannel(ANDROID_CHANNEL_ID, name, NotificationManager.IMPORTANCE_LOW)
        channel.enableLights(false)
        channel.enableVibration(false)
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        notificationManager.createNotificationChannel(channel)
        var intent: Intent? = Intent(context, MapsActivity::class.java)
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addNextIntent(intent!!)
        val notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(context, ANDROID_CHANNEL_ID)
        try {
            builder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Maps Demo App")
                .setContentText("Tap to open")
                .setContentIntent(notificationPendingIntent)
            builder.setAutoCancel(true)
        } catch (e: Exception) {
        }
        return builder.build()
    }

    fun cancelNotification(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.cancel(NOTIFICATION_ID)
        }
    }
}