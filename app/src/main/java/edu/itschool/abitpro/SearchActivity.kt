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
            val query = editText.text.toString()
            val listPrograms = arrayListOf(
                binding.table11.text.text.toString().trim(),
                binding.table12.text.text.toString().trim(),
                binding.table13.text.text.toString().trim(),
                binding.table14.text.text.toString().trim()
            ).filter { it.isNotEmpty() } as ArrayList<String>

            val budgBall = binding.budgBallValueEntry.searchByName.text.toString().trim().toIntOrNull()
            val budgPlace = binding.budgPlaceValueEntry.searchByName.text.toString().trim().toIntOrNull()
            val payBall = binding.paidBallValueEntry.searchByName.text.toString().trim().toIntOrNull()
            val payPlace = binding.paidPlacesValueEntry.searchByName.text.toString().trim().toIntOrNull()
            val cost = binding.paidCostValueEntry.searchByName.text.toString().trim().toIntOrNull()
            val course = binding.coursesValueParam.priceParam.text.toString().trim().toIntOrNull()
            val city = binding.cityValueParam.textParamNocross.text.toString().trim()
            val warCaf = binding.warValueParam.textParamNocross.text.toString().trim().toIntOrNull()

            Log.i("Info9", "query = $query")

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("SEARCH_QUERY", query)
                putStringArrayListExtra("KEY_PROGRAMS",listPrograms)
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
