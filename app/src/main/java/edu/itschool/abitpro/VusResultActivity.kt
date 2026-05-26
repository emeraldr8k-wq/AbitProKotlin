package edu.itschool.abitpro

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.itschool.abitpro.data.dto.HeiDto
import edu.itschool.abitpro.databinding.ActivityVusResultBinding

class VusResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityVusResultBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val hei = intent.getSerializableExtra("UNIVERSITIES") as? HeiDto



        if (hei != null) {
            //todo вписать айди из биндинга, с сервером
         binding.vuzName.text = hei.name
//                binding.singlBudgBall.text = hei.budgBall
//                binding.singlBudgPlace.text = hei.budgPlace
//                binding.includedPaidBall.text       text = hei.paidBall
//                findViewById<TextView>(R.id.paidPlace).text = hei.paidPlace


        } else {
            Log.i("Info9", "create")
        }

    }


}