package edu.itschool.abitpro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import edu.itschool.abitpro.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private var binding: ActivitySearchBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(getLayoutInflater())
    }
}