package edu.itschool.abitpro

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.itschool.abitpro.R
import edu.itschool.abitpro.data.UniversityRepository
import edu.itschool.abitpro.data.dto.HeiDto
import edu.itschool.abitpro.databinding.ActivityVusResultBinding
import edu.itschool.abitpro.domain.model.Hei

class VusResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityVusResultBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val heiId = intent.getIntExtra("UNIVERSITY_ID", -1)
        Log.i("Info9", "HeiId =  ${heiId}")




        if (heiId != -1) {

            var selectedHei = UniversityRepository.universityList.find { it.id == heiId }


            if (selectedHei == null) {
                selectedHei = Hei(
                    id = heiId,
                    name = "МФТИ (Вуз из базы)",
                    city = "Москва",
                    description = "Успешный переход! Данные подтянулись по ID: $heiId",
                    "",
                    emptyList(),
                    7,
                    4
                )
            }


            selectedHei.let { hei ->
                Log.i("Info9", "дошли")
                binding.vuzNameValue.text = hei?.name
                binding.budgBallValue.root.text = hei?.freePassingGrade.toString()
                binding.budgPlaceValue.root.text = hei?.freePlace.toString()
                binding.includedPaidBall.root.text = hei?.payPassingGrade.toString()
                binding.includedPaidPlace.root.text = hei?.payPlace.toString()
                binding.paidCostValue.root.text = hei?.cost.toString()
                binding.coursesValue.root.text = hei?.rating.toString()
                binding.cityValue.root.text = hei?.city







                //todo 3 направления





            }

        }


    }
}