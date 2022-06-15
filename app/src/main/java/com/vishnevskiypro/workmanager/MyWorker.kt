package com.vishnevskiypro.workmanager

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.util.*

class MyWorker(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    val TAG: String = "workmng"


    override fun doWork(): Result {
        Log.d(TAG, "doWork Start: ")

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        try {

            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MyLog.txt")
            file.appendText("\n $hour $minute")

        } catch (e: InterruptedException){
            Log.d(TAG, "Somthing went wrong $e")
        }


        Log.d(TAG, "doWork end: ")

        return Result.success()

    }
}