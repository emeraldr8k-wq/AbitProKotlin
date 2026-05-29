package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.itschool.abitpro.data.UniversityRepository
import edu.itschool.abitpro.databinding.ActivityVusResultBinding
import edu.itschool.abitpro.domain.model.Hei
import kotlinx.coroutines.launch

class VusResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityVusResultBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(binding.root)

        val favBtn = binding.searchEntry.searchButton

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

        val heiId = intent.getIntExtra("UNIVERSITY_ID", -1)
        Log.i("Info9", "HeiId =  $heiId")




        if (heiId != -1) {

            lifecycleScope.launch {
                try {
                    val selectedHei = UniversityRepository.getHeiById(heiId, applicationContext)
                    updateUi(selectedHei)
                } catch (e: Exception) {
                    Log.e("Info9", "Ошибка загрузки вуза", e)
                }
                val isFavorite =
                    UniversityRepository.getFavoritesIds(applicationContext).contains(heiId)
                updateFavBtnIcon(favBtn, isFavorite)


            }
            favBtn.setOnClickListener {
                lifecycleScope.launch {
                    val isNowFavorite =
                        UniversityRepository.toAdRemoveFavorite(applicationContext, heiId)
                    updateFavBtnIcon(favBtn, isNowFavorite)
                }

            }

        }


    }

    private fun updateUi(selectedHei: Hei?) {
        selectedHei.let { hei ->
            Log.i("Info9", "дошли")
            if (hei != null) {
                binding.vuzNameValue.text = hei.name
                binding.budgBallValue.root.text = hei.freePassingGrade.toString()
                binding.budgPlaceValue.root.text = hei.freePlace.toString()
                binding.includedPaidBall.root.text = hei.payPassingGrade.toString()
                binding.includedPaidPlace.root.text = hei.payPlace.toString()
                binding.paidCostValue.price.text = hei.cost.toString()
                binding.coursesValue.price.text = hei.introCoursesPrice.toString()
                binding.cityValue.text.text = hei.city
                if (hei.isMilitary) {
                    binding.warValue.text.text =
                        getString(R.string.course, hei.militaryFromCourse)
                } else {
                    binding.warValue.text.text = getString(R.string.no)
                }


                val p = hei.programs
                binding.table11.text.text = p.getOrNull(0) ?: "-"
                binding.table12.text.text = p.getOrNull(1) ?: "-"
                binding.table13.text.text = p.getOrNull(2) ?: "-"
                binding.table14.text.text = p.getOrNull(3) ?: "-"
            }
        }
    }

    private fun updateFavBtnIcon(button: ImageButton, isFavorite: Boolean) {
        if (isFavorite) {
            button.setImageResource(R.drawable.image_heart_default_filled)
        } else {
            button.setImageResource(R.drawable.image_heart_default)
        }
    }
}