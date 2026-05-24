package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        Log.i("Info9", "createMA")

        //test
        val intent = Intent(
            this,
            ResultActivity::class.java
        ).apply {}
        startActivity(intent)


    }
}