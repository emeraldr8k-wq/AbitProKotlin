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

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomBar.bottomButtonProfile.setOnClickListener {
            val favoriteIntent = Intent(this, FavouritesActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(favoriteIntent)
        }

        val editText: EditText = binding.searchEntry.searchByNameEntry
        val btn: ImageButton = binding.searchEntry.searchButton

        btn.setOnClickListener {
            val query = editText.text.toString()
            val listPrograms = arrayListOf(
                binding.table11.text.text.toString().trim(),
                binding.table12.text.text.toString().trim(),
                binding.table13.text.text.toString().trim(),
                binding.table14.text.text.toString().trim()
            ).filter { it.isNotEmpty() } as ArrayList<String>

            val budgBall = binding.budgBallValueEntry.num.text.toString().trim().toIntOrNull()
            val budgPlace = binding.budgPlaceValueEntry.num.text.toString().trim().toIntOrNull()
            val payBall = binding.paidBallValueEntry.num.text.toString().trim().toIntOrNull()
            val payPlace = binding.paidPlacesValueEntry.num.text.toString().trim().toIntOrNull()
            val cost = binding.paidCostValueEntry.priceParam.text.toString().trim().toIntOrNull()
            val course = binding.coursesValueParam.priceParam.text.toString().trim().toIntOrNull()
            val city = binding.cityValueParam.text.text.toString().trim()
            val warCaf = binding.checkBox.isChecked

            Log.i("Info9", "query = $query")

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("SEARCH_QUERY", query)
                putStringArrayListExtra("KEY_PROGRAMS", listPrograms)
                putExtra("KEY_budgBall", budgBall)
                putExtra("KEY_budgPlace", budgPlace)
                putExtra("KEY_payBall", payBall)
                putExtra("KEY_payPlace", payPlace)
                putExtra("KEY_cost", cost)
                putExtra("KEY_course", course)
                putExtra("KEY_city", city)
                putExtra("KEY_warCaf", warCaf)
            }
            startActivity(intent)
        }


    }

}
