package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import edu.itschool.abitpro.data.UniversityRepository
import edu.itschool.abitpro.data.dto.HeiDto
import edu.itschool.abitpro.databinding.ActivityResultBinding
import kotlinx.coroutines.launch
import edu.itschool.abitpro.data.mapper.toHei
import edu.itschool.abitpro.data.mapper.toHeiList

class ResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityResultBinding.inflate(layoutInflater) }

    private var adapter: SearchAdapter? = null
    private val editText: EditText? = binding?.searchEntry?.searchByNameEntry

    private var recyclerView: RecyclerView? = null
    private val btn: ImageButton? = binding?.searchEntry?.searchButton

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
            ).apply { putExtra("UNIVERSITY_ID", clickedItem.id) }
            startActivity(vusIntent)


        }

        //todo
        btn?.setOnClickListener { val query = editText?.text.toString().trim()}


        val searchQuery = intent.getStringExtra("SEARCH_QUERY") ?: ""


        lifecycleScope.launch {

            val listHei = UniversityRepository.searchByName(searchQuery)


            adapter?.submitList(listHei)


            if (searchQuery.isNotEmpty()) {
                editText?.setText(searchQuery)
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
}