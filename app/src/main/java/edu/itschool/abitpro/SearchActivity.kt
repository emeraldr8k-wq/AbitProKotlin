package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import edu.itschool.abitpro.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private var binding: ActivitySearchBinding? = null
    private val editText: EditText? = binding?.searchEntry?.searchByNameEntry
    private val btn: ImageButton? = binding?.searchEntry?.searchButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(getLayoutInflater())

        btn?.setOnClickListener {
            val query = editText?.text.toString().trim()


            val intent = Intent(this, ResultActivity::class.java).apply {

                putExtra("SEARCH_QUERY", query)
            }
            startActivity(intent)
        }

    }

}
