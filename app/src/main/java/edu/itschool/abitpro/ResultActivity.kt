package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.itschool.abitpro.data.UniversityRepository
import edu.itschool.abitpro.databinding.ActivityResultBinding
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var adapter: SearchAdapter? = null
    private var curSearchMode = "FAST_SEARCH"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("Info9", "createRA")


        binding.bottomBar.bottomButtonSearch.setOnClickListener {
            val searchIntent = Intent(this, SearchActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(searchIntent)
        }
        binding.bottomBar.bottomButtonFavourites.setOnClickListener {
            val favoriteIntent = Intent(this, FavouritesActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(favoriteIntent)
        }





        Log.i("Info9", "Размер списка в репозитории: ${UniversityRepository.universityList.size}")


        setupRecyclerView()

        val searchQuery = intent.getStringExtra("SEARCH_QUERY") ?: "FAST_SEARCH"

        curSearchMode = intent.getStringExtra("FROM_SCREEN") ?: ""
        Log.i("Info9", "Получен поисковый запрос: ${searchQuery}")
        if (searchQuery.isNotEmpty()) {
            binding.searchEntry.searchByNameEntry.setText(searchQuery)
        }


        performSearch(searchQuery)


        binding.searchEntry.searchButton.setOnClickListener {
            val curQuery = binding.searchEntry.searchByNameEntry.text.toString().trim()

            curSearchMode = "FAST_SEARCH"
            performSearch(curQuery)
        }


    }

    private fun performSearch(searchQuery: String) {
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            adapter?.submitList(emptyList())
            binding.searchRes.text = getString(R.string.search_result)

            UniversityRepository.loadUniversities(applicationContext)

            val listHei = if (curSearchMode == "FAST_SEARCH") {
                Log.i("Info9", "Выполняется поиск по названию: $searchQuery")
                UniversityRepository.searchByName(searchQuery)
            } else {


                val listPrograms = intent.getStringArrayListExtra("KEY_PROGRAMS") ?: emptyList()
                val budgBall = intent.getIntExtra("KEY_budgBall", -1)
                val budgPlace = intent.getIntExtra("KEY_budgPlace", -1)
                val payBall = intent.getIntExtra("KEY_payBall", -1)
                val payPlace = intent.getIntExtra("KEY_payPlace", -1)
                val cost = intent.getIntExtra("KEY_cost", -1)
                val course = intent.getIntExtra("KEY_course", -1)
                val city = intent.getStringExtra("KEY_city") ?: ""
                val warCaf = intent.getBooleanExtra("KEY_warCaf", false)


                UniversityRepository.getFilteredUniversities(
                    searchQuery = searchQuery,
                    listPrograms = listPrograms,
                    budgPlace = budgPlace,
                    budgBall = budgBall,
                    payBall = payBall,
                    payPlace = payPlace,
                    city = city,
                    cost = cost,
                    course = course,
                    warCaf = warCaf
                )


            }
            adapter?.submitList(listHei)
            binding.progressBar.visibility = View.GONE
            if (listHei.isEmpty()) binding.searchRes.text = getString(R.string.empty_result)
        }
    }


    private fun setupRecyclerView() {
        adapter = SearchAdapter { clickedItem ->
            val vusIntent = Intent(
                this, VusResultActivity::class.java
            ).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                putExtra("UNIVERSITY_ID", clickedItem.id)
            }
            startActivity(vusIntent)


        }
        binding.recyclerView.setAdapter(adapter)
    }
}