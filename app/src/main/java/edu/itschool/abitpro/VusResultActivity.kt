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
        val heiId = intent.getIntExtra("UNIVERSITIES_ID", -1)




        if (heiId != -1) {

            var selectedHei = UniversityRepository.universityList.find { it.id == heiId }

            // ЗАГЛУШКА ДЛЯ ТЕСТА: Если в репозитории пусто, создаем этот вуз прямо здесь по ID,
            // чтобы фронтенд-часть работала прямо сейчас!
            if (selectedHei == null) {
                selectedHei = Hei(
                    id = heiId,
                    name = "МФТИ (Вуз из базы)",
                    city = "Москва",
                    description = "Успешный переход! Данные подтянулись по ID: $heiId",
                    "",
                    7,
                    true,
                    4
                )
            }


            selectedHei.let { hei ->
                binding.vuzName.text = hei?.name
                binding.singlBudgBall.text = hei?.budgBall.toString()
                binding.singlBudgPlace.text = hei?.budgPlace.toString()
                findViewById<TextView>(R.id.included_paid_ball).text = hei?.paidBall.toString()
                findViewById<TextView>(R.id.included_paid_place).text = hei?.paidPlace.toString()


                //todo вписать айди из биндинга, с сервером


//                findViewById<TextView>(R.id.paidPlace).text = hei.paidPlace


            }

        }


    }
}