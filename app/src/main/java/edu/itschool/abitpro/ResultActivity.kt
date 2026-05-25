package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.itschool.abitpro.data.UniversityRepository
import edu.itschool.abitpro.databinding.ActivityResultBinding
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var adapter: SearchAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("Info9", "createRA")




        UniversityRepository.loadUniversities(this)
        Log.i("Info9", "Размер списка в репозитории: ${UniversityRepository.universityList.size}")


        setupRecyclerView()

        val searchQuery = intent.getStringExtra("SEARCH_QUERY") ?: ""
        Log.i("Info9", "query = ${searchQuery}")
        if (searchQuery.isNotEmpty()) {
            binding.searchEntry.searchByNameEntry.setText(searchQuery)
        }


        performSearch(searchQuery)


        binding.searchEntry.searchButton.setOnClickListener {
            val curQuery = binding.searchEntry.searchByNameEntry.text.toString().trim()
            performSearch(curQuery)
        }


    }

    private fun performSearch(searchQuery: String) {
        lifecycleScope.launch {

            val listHei = if (searchQuery.isEmpty()) {
                UniversityRepository.universityList
            } else {
                UniversityRepository.searchByName(searchQuery)
            }



            adapter?.submitList(listHei)

        }
    }


    private fun setupRecyclerView() {
        adapter = SearchAdapter { clickedItem ->
            val vusIntent = Intent(
                this,
                VusResultActivity::class.java
            ).apply { putExtra("UNIVERSITY_ID", clickedItem.id) }
            startActivity(vusIntent)


        }
        binding.recyclerView.setAdapter(adapter)
    }
}