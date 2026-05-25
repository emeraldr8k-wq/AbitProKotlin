package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import edu.itschool.abitpro.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(getLayoutInflater())
        setContentView(binding.root)

        val editText: EditText = binding.searchEntry.searchByNameEntry
        val btn: ImageButton = binding.searchEntry.searchButton

        btn.setOnClickListener {
            val query = editText.text.toString().trim()
            Log.i("Info9", "query = $query")
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("SEARCH_QUERY", query)
            }
            startActivity(intent)
        }

    }

}
