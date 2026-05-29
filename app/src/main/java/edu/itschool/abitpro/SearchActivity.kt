package edu.itschool.abitpro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListPopupWindow
import androidx.appcompat.app.AppCompatActivity
import edu.itschool.abitpro.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomBar.bottomButtonFavourites.setOnClickListener {
            val favoriteIntent = Intent(this, FavouritesActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            startActivity(favoriteIntent)
        }


        val editText: EditText = binding.searchEntry.searchByNameEntry
        val btn: ImageButton = binding.searchEntry.searchButton
        val defPrograms = listOf(
            getString(R.string.def_val_pop_up_window),
            "Бизнес-информатика",
            "Программная инженерия",
            "Экономика",
            "Менеджмент",
            "Прикладная математика",
            "Физика",
            "Химия",
            "Биология",
            "Юриспруденция",
            "Информатика",
            "Биоинформатика",
            "Фундаментальная информатика",
            "Математика"
        )
        val defCity = listOf(
            getString(R.string.def_val_pop_up_window),
            "Москва",
            "Новосибирск",
            "Долгопрудный",
            "Санкт-Петербург"
        )
        val programsAdapter = ArrayAdapter(this, R.layout.prefab_list_item_1, defPrograms)
        listOf(
            binding.table11.text, binding.table12.text, binding.table13.text
        ).forEach { curTv ->
            val programPopupWindow = ListPopupWindow(this).apply {
                setAdapter(programsAdapter)

                anchorView = curTv

                setOnItemClickListener { _, _, position, _ ->
                    val chosenProgram = defPrograms[position]
                    curTv.text = chosenProgram
                    dismiss()
                }


            }
            curTv.setOnClickListener {
                programPopupWindow.show()
            }

        }
        val citiesAdapter = ArrayAdapter(this, R.layout.prefab_list_item_1, defCity)
        val cityPopupWindow = ListPopupWindow(this).apply {
            setAdapter(citiesAdapter)

            anchorView = binding.cityValueParam.text

            setOnItemClickListener { _, _, position, _ ->
                val chosenCity = defCity[position]
                binding.cityValueParam.text.text = chosenCity
                dismiss()
            }


        }
        binding.cityValueParam.text.setOnClickListener {
            cityPopupWindow.show()
        }


        btn.setOnClickListener {
            val query = editText.text.toString()
            val listPrograms = arrayListOf(
                binding.table11.text.text.toString().trim(),
                binding.table12.text.text.toString().trim(),
                binding.table13.text.text.toString().trim(),
                binding.table14.text.text.toString().trim()
            ).filterTo(ArrayList()) {
                it != getString(R.string.select_type).trim() && it != getString(R.string.def_val_pop_up_window)
            }

            val budgBall = binding.budgBallValueEntry.num.text.toString().trim().toIntOrNull()
            val budgPlace = binding.budgPlaceValueEntry.num.text.toString().trim().toIntOrNull()
            val payBall = binding.paidBallValueEntry.num.text.toString().trim().toIntOrNull()
            val payPlace = binding.paidPlacesValueEntry.num.text.toString().trim().toIntOrNull()
            val cost = binding.paidCostValueEntry.priceParam.text.toString().trim().toIntOrNull()
            val course = binding.coursesValueParam.priceParam.text.toString().trim().toIntOrNull()
            val city = if ((binding.cityValueParam.text.text.toString().trim() != getString(R.string.def_val_pop_up_window)) &&
                (binding.cityValueParam.text.text.toString().trim() != getString(R.string.select_type).trim())
            ) {
                binding.cityValueParam.text.text.toString().trim()
            } else {
                ""
            }
            val warCaf = binding.checkBox.isChecked

            Log.i("Info9", "query = $query")

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("SEARCH_QUERY", query)
                putExtra("FROM_SCREEN", "ADVANCED_SEARCH")
                putStringArrayListExtra("KEY_PROGRAMS", listPrograms)
                putExtra("KEY_budgBall", budgBall)
                putExtra("KEY_budgPlace", budgPlace)
                putExtra("KEY_payBall", payBall)
                putExtra("KEY_payPlace", payPlace)
                putExtra("KEY_cost", cost)
                putExtra("KEY_course", course)
                putExtra("KEY_city", city)
                putExtra("KEY_warCaf", warCaf)
            }
            startActivity(intent)
        }


    }

}
