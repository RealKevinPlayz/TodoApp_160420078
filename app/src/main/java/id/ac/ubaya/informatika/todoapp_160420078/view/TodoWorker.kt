package id.ac.ubaya.informatika.todoapp_160420078.view

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import id.ac.ubaya.informatika.todoapp_160420078.util.NotificationHelper

class TodoWorker(val context: Context, val params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        NotificationHelper(context).createNotification(
            inputData.getString("title").toString(),
            inputData.getString("message").toString()
        )
        return Result.success()
    }

}