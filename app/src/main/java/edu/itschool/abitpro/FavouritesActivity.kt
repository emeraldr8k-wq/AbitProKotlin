package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.itschool.abitpro.data.UniversityRepository
import edu.itschool.abitpro.databinding.ActivityFavouritesBinding
import edu.itschool.abitpro.databinding.ActivitySearchBinding
import kotlinx.coroutines.launch

class FavouritesActivity: AppCompatActivity() {

    private lateinit var binding: ActivityFavouritesBinding
    private var adapter: SearchAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomBar.bottomButtonSearch.setOnClickListener {
            val searchIntent = Intent(this, SearchActivity::class.java)
            startActivity(searchIntent)
        }

        setupRecyclerView()

        val editText: EditText = binding.searchEntry.searchByNameEntry
        val btn: ImageButton = binding.searchEntry.searchButton


    }

    override fun onResume() {
        super.onResume()
        loadFavorites()
    }

    private fun loadFavorites() {
        lifecycleScope.launch {
            UniversityRepository.loadUniversities(this@FavouritesActivity)
            val favoriteIds = UniversityRepository.getFavoritesIds(this@FavouritesActivity)
            Log.i("Info9", "ID избранных вузов: $favoriteIds")
            val favoriteList = UniversityRepository.universityList.filter { vus ->
                favoriteIds.contains(vus.id)
            }
            Log.i("Info9", "Отрисовка ${favoriteList.size} избранных вузов")
            adapter?.submitList(favoriteList)
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
        binding.recyclerViewFavourites.setAdapter(adapter)
    }

}