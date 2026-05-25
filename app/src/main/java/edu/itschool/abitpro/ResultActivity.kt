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
        Log.i("Info9", "Получен поисковый запрос: ${searchQuery}")
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

            val allUniversities = UniversityRepository.universityList

            val listPrograms = intent.getStringArrayListExtra("KEY_PROGRAMS") ?: emptyList()
            val budgBall = intent.getIntExtra("KEY_budgBall", -1)
            val budgPlace = intent.getIntExtra("KEY_budgPlace", -1)
            val payBall = intent.getIntExtra("KEY_payBall", -1)
            val payPlace = intent.getIntExtra("KEY_payPlace", -1)
            val cost = intent.getIntExtra("KEY_cost", -1)
            val course = intent.getIntExtra("KEY_course", -1)
            val city = intent.getStringExtra("KEY_city") ?: ""
            val warCaf = intent.getIntExtra("KEY_warCaf", -1)


            val listHei = allUniversities.filter { vus ->
                val matchesName = searchQuery.isEmpty() || vus.name.contains(
                    searchQuery, ignoreCase = true
                )

                val matchPrograms = listPrograms.isEmpty() || listPrograms.any { subject -> vus.programs.toSet().contains(subject) }
                val matchesBudgBall = budgBall == null || (vus.freePassingGrade ?: 0) <= budgBall
                val matchesBudgPlace = budgPlace == null || (vus.freePlace ?: 0) >= budgPlace
                val matchesPayBall = payBall == null || (vus.payPassingGrade ?: 0) <= payBall
                val matchesPayPlace = payPlace == null || (vus.payPlace ?: 0) >= payPlace
                val matchesCost = cost == null || (vus.cost ?: 0) <= cost
                val matchesCourse = course == null || (vus.introCoursesPrice ?: 0) <= course
                val matchesCity = city.isEmpty() || vus.city.contains(city, ignoreCase = true)
                val matchesWarCaf = warCaf == null || vus.isMilitary // todo && (vus.militaryFromCourse ?: 0)


                matchesName && matchesBudgBall && matchesBudgPlace && matchesPayBall && matchesPayPlace && matchesCity && matchesCourse && matchesCost && matchesWarCaf && matchPrograms
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