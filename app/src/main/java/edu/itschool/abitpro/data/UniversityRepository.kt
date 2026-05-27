package edu.itschool.abitpro.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.itschool.abitpro.data.dto.HeiDto
import edu.itschool.abitpro.data.mapper.toHei
import edu.itschool.abitpro.data.mapper.toHeiList
import edu.itschool.abitpro.data.network.RetrofitClient.apiService
import edu.itschool.abitpro.domain.model.Hei
import java.nio.charset.StandardCharsets

object UniversityRepository {
    var universityList: List<Hei> = emptyList()
        private set
    private const val PREFS_NAME = "abitpro_favorites"
    private const val KEY_FAVORITES = "favorite_ids"

    suspend fun loadUniversities(context: Context) {
        if (universityList.isNotEmpty()) return

        val service = apiService
        if (service != null) {
            try {
                val networkDtoList = service.getUniversities()
                universityList = networkDtoList.toHeiList()
                Log.i("Info9", "Данные с сервера успешно загружены! Размер: ${universityList.size}")
                return
            } catch (e: Exception) {
                Log.e("Info9", "Сервер недоступен. Ошибка: ${e.message}")
            }
        }


        try {
            val jsonString = context.assets.open("universities.json").use { inputStream ->
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                String(buffer, StandardCharsets.UTF_8)
            }

            val gson = Gson()
            val listType = object : TypeToken<List<HeiDto>>() {}.type
            val dtoHei: List<HeiDto> = gson.fromJson(jsonString, listType)
            universityList = dtoHei.map { it.toHei() }
            Log.i("Info9", "Данные успешно загружены! Размер: ${universityList.size}")
        } catch (e: Exception) {
            Log.e("Info9", "Ошибка при чтении или парсинге JSON!")

        }

    }

    suspend fun searchByName(query: String): List<Hei> {

        val service = apiService
        if (service != null) {
            try {
                Log.i("NetworkSearch", "Делаем запрос к серверу для: $query")

                return service.searchUniversities(query).toHeiList()
            } catch (e: Exception) {
                Log.e("NetworkSearch", "Сервер недоступен")

            }


        }
        return universityList
    }

    suspend fun getHeiById(id: Int, context: Context): Hei? {
        val service = apiService
        if (service != null) {
            try {
                Log.i("NetworkDetails", "Запрос к серверу для вуза по ID: $id")

                val dto = service.getUniversityById(id.toLong())
                return dto.toHei()

            } catch (e: Exception) {
                Log.e("NetworkDetails", "Сервер недоступен. Ошибка: ${e.message}")
            }
        }

        if (universityList.isEmpty()) loadUniversities(context)
        return universityList.find { it.id == id }
    }

    fun getFavoritesIds(context: Context): Set<Int> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val stringSet = prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
        return stringSet.mapNotNull { it.toIntOrNull() }.toSet()
    }

    fun toAdRemoveFavorite(context: Context, id: Int): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val curFavorites = getFavoritesIds(context).toMutableSet()

        val isNowFavorite = if (curFavorites.contains(id)) {
            curFavorites.remove(id)
            false
        } else {
            curFavorites.add(id)
            true
        }

        val stringSet = curFavorites.map {it.toString()}.toSet()
        prefs.edit().putStringSet(KEY_FAVORITES, stringSet).apply()
        return isNowFavorite
    }

}


