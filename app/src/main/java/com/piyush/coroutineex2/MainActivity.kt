package com.piyush.coroutineex2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.Main).launch {
            launch { task1() } // This will run task1() and task2() in parallel
            launch {  task2() }
//            task1() // This will run task1() and task2() sequentially
//            task2()
        }

    }

    suspend fun task1() {
        Log.d("task1", "Task 1 started")
        yield()
        delay(1000)
        Log.d("task1", "Task 1 ended")
    }

    // similarly define task2()
     suspend fun task2() {
        Log.d("task2", "Task 2 started")
        yield()
        delay(1000)
        Log.d("task2", "Task 2 ended")
    }


}