package edu.itschool.abitpro.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.itschool.abitpro.data.dto.HeiDto
import java.io.IOException
import java.nio.charset.StandardCharsets

object UniversityRepository {
    var universityList: List<HeiDto> = emptyList()
        private set

    fun loadUniversities(context: Context) {
        if (universityList.isNotEmpty()) return
        try {
            val jsonString = context.assets.open("universities.json").use { inputStream ->
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                String(buffer, StandardCharsets.UTF_8)
            }

            val gson = Gson()
            val listType = object : TypeToken<List<HeiDto>>() {}.type
            universityList = gson.fromJson(jsonString, listType)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}