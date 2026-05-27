package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import edu.itschool.abitpro.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    val binding: ActivityIntroBinding by lazy { ActivityIntroBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        setContentView(binding.root)
        Log.i("Info9", "до интента")


        binding.startSearchButton.setOnClickListener {
            Log.i("Info9", "клик")
            val searchIntent = Intent(this, SearchActivity::class.java).apply { }
            startActivity(searchIntent)
            finish()
        }
    }
}