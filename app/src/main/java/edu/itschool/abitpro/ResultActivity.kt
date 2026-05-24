package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import edu.itschool.abitpro.data.UniversityRepository
import edu.itschool.abitpro.data.dto.HeiDto
import edu.itschool.abitpro.databinding.ActivityResultBinding

 class ResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityResultBinding.inflate(layoutInflater) }

    private var adapter: SearchAdapter? = null

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(binding.root)
        UniversityRepository.loadUniversities(this)
        Log.i("Info9", "Размер списка в репозитории: ${UniversityRepository.universityList.size}")


//todo
        adapter = SearchAdapter { clickedItem ->
            val vusIntent = Intent(
                this,
                VusResultActivity::class.java
            ).apply { putExtra("UNIVERSITY_ID", clickedItem.id)  }
            startActivity(vusIntent)


        }




        val testList = listOf(
            HeiDto(1, "МФТИ (Тестовый вуз)", "Москва", "290", "150", 240, true, 4)
        ) //todo
        recyclerView = binding.recyclerView
        recyclerView?.setAdapter(adapter)

//        if (UniversityRepository.universityList != emptyList()) { //Condition is always 'true'.

            adapter?.submitList(UniversityRepository.universityList)

//        } else {
//            Log.i("Info9", "UniversityRepository.universityList = null")
//        }
    }
}