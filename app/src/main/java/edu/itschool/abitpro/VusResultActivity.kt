package edu.itschool.abitpro

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.itschool.abitpro.data.mapper.toHei
import edu.itschool.abitpro.data.network.RetrofitClient
import edu.itschool.abitpro.databinding.ActivityVusResultBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VusResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityVusResultBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val heiId = intent.getIntExtra("UNIVERSITY_ID", -1)
        Log.i("Info9", "HeiId =  ${heiId}")




        if (heiId != -1) {
            lifecycleScope.launch {
                try {
                    val dto = withContext(Dispatchers.IO) {
                        RetrofitClient.apiService.getUniversityById(heiId.toLong())
                    }
                    val hei = dto.toHei()

                    binding.vuzNameValue.text = hei.name
                    binding.budgBallValue.root.text = hei.freePassingGrade.toString()
                    binding.budgPlaceValue.root.text = hei.freePlace.toString()
                    binding.includedPaidBall.root.text = hei.payPassingGrade.toString()
                    binding.includedPaidPlace.root.text = hei.payPlace.toString()
                    binding.paidCostValue.root.text = hei.cost.toString()
                    binding.coursesValue.root.text = hei.introCoursesPrice.toString()
                    binding.cityValue.root.text = hei.city

                    val p = hei.programs
                    binding.table11.text.text = p.getOrNull(0) ?: "-"
                    binding.table12.text.text = p.getOrNull(1) ?: "-"
                    binding.table13.text.text = p.getOrNull(2) ?: "-"
                    binding.table14.text.text = p.getOrNull(3) ?: "-"
                } catch (e: Exception) {
                    Log.e("Info9", "Ошибка загрузки вуза", e)
                }
            }
        }
    }


}
