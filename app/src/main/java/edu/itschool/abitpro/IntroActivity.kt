package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.itschool.abitpro.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    val searchIntent = Intent(this, SearchActivity::class.java).apply {  }
    val binding: ActivityIntroBinding by lazy { ActivityIntroBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        setContentView(binding.getRoot())
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })
        val searchIntent = Intent(this, SearchActivity::class.java).apply {  }
//todo починить
        binding.startSearchButton.setOnClickListener {
            Log.i("Info9", "клик")
            startActivity(searchIntent)
        }
    }
}