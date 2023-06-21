package id.ac.ubaya.informatika.todoapp_160420078.util

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ubaya.informatika.todoapp_160420078.R
import id.ac.ubaya.informatika.todoapp_160420078.view.MainActivity

class NotificationHelper(val context: Context) {
    private val CHANNEL_ID = "todo_channel_id"
    private val CHANNEL_NAME = "todo"
    private val NOTIFICATION_ID = 1

    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Todo channel description"

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(title:String, message:String){
        createNotificationChannel()

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.todochar)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.checklist)
            .setLargeIcon(icon)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(icon).bigLargeIcon(icon)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

//        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
//
//        }

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
    }
}
