package edu.itschool.abitpro

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.itschool.abitpro.data.UniversityRepository
import edu.itschool.abitpro.databinding.ActivityVusResultBinding

class VusResultActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val binding = ActivityVusResultBinding.inflate(getLayoutInflater())
        val heiId = intent.getIntExtra("UNIVERSITIES_ID", -1)
        if (heiId != -1) {


            val selectedHei = UniversityRepository.universityList.find { it.id == heiId }


            selectedHei?.let { hei ->
                //todo вписать айди из биндинга
                findViewById<TextView>(binding.vuzName).text = hei.name       // Название вуза
                findViewById<TextView>(binding.).text = hei.budgBall       // Бюджетный балл
                findViewById<TextView>(R.id.budgPlace).text = hei.budgPlace     // Бюджетные места
                findViewById<TextView>(R.id.paidBall).text = hei.paidBall       // Платный балл
                findViewById<TextView>(R.id.paidPlace).text = hei.paidPlace     // Платные места
            }
        }

    }


    

}