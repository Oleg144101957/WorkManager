package com.vishnevskiypro.workmanager

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.*
import com.vishnevskiypro.workmanager.databinding.ActivityMainBinding
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val TAG: String = "workmng"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        val request = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES).build()

        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(path, "MyLog.txt")
        file.createNewFile()

        Log.d(TAG, "onCreate start")

        binding.button.setOnClickListener {
            WorkManager.getInstance().enqueueUniquePeriodicWork("Periodic Work", ExistingPeriodicWorkPolicy.REPLACE, request)
        }

    }



}