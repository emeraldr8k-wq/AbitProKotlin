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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.StandardCharsets

object UniversityRepository {
    var universityList: List<Hei> = emptyList()
        private set
    private const val PREFS_NAME = "abitpro_favorites"
    private const val KEY_FAVORITES = "favorite_ids"

    suspend fun loadUniversities(context: Context) = withContext(Dispatchers.IO) {
        if (universityList.isNotEmpty()) return@withContext
        val appContext = context.applicationContext

        val service = apiService
        try {
            val networkDtoList = service.getUniversities()
            universityList = networkDtoList.toHeiList()
            Log.i("Info9", "Данные с сервера успешно загружены! Размер: ${universityList.size}")
            return@withContext
        } catch (e: Exception) {
            Log.e("Info9", "Сервер недоступен. Ошибка: ${e.message}")
        }


        try {
            val jsonString = appContext.assets.open("universities.json").use { inputStream ->
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
        if (query.isEmpty()) return universityList
        val service = apiService
        try {
            Log.i("NetworkSearch", "Делаем запрос к серверу для: $query")

            return service.searchUniversities(query).toHeiList()
        } catch (e: Exception) {
            Log.e("NetworkSearch", "Сервер недоступен")

        }
        return withContext(Dispatchers.Default) {
            universityList.filter { vus ->
                vus.name.contains(query, ignoreCase = true)
            }
        }
    }


    suspend fun getHeiById(id: Int, context: Context): Hei? {
        val service = apiService
        try {
            Log.i("NetworkDetails", "Запрос к серверу для вуза по ID: $id")

            val dto = service.getUniversityById(id.toLong())
            return dto.toHei()

        } catch (e: Exception) {
            Log.e("NetworkDetails", "Сервер недоступен. Ошибка: ${e.message}")
        }

        if (universityList.isEmpty()) loadUniversities(context)
        return universityList.find { it.id == id }
    }

    suspend fun getFavoritesIds(context: Context): Set<Int> = withContext(Dispatchers.IO) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val stringSet = prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
        stringSet.mapNotNull { it.toIntOrNull() }.toSet()
    }

    suspend fun toAdRemoveFavorite(context: Context, id: Int): Boolean =
        withContext(Dispatchers.IO) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val curFavorites = getFavoritesIds(context).toMutableSet()

            val isNowFavorite = if (curFavorites.contains(id)) {
                curFavorites.remove(id)
                false
            } else {
                curFavorites.add(id)
                true
            }

            val stringSet = curFavorites.map { it.toString() }.toSet()
            prefs.edit().putStringSet(KEY_FAVORITES, stringSet).apply()
            isNowFavorite
        }

    suspend fun getFilteredUniversities(
        searchQuery: String,
        listPrograms: List<String>,
        budgBall: Int,
        budgPlace: Int,
        payBall: Int,
        payPlace: Int,
        cost: Int,
        course: Int,
        city: String,
        warCaf: Boolean
    ): List<Hei> = withContext(Dispatchers.Default){


        val allUniversities = universityList
        Log.i("Info9", "Фильтрация началась. Найдено вузов: ${allUniversities.size}")

        allUniversities.filter { vus ->
            val matchesName =
                searchQuery.isEmpty() || vus.name.contains(searchQuery, ignoreCase = true)
            val vusProgramsSet = vus.programs.map { it.lowercase() }.toSet()
            val matchPrograms = listPrograms.isEmpty() || listPrograms.any { userProgram ->
                vusProgramsSet.contains(userProgram.lowercase())
            }
            val matchesBudgBall = budgBall == -1 || (vus.freePassingGrade ?: 0) <= budgBall
            val matchesBudgPlace = budgPlace == -1 || (vus.freePlace ?: 0) >= budgPlace
            val matchesPayBall = payBall == -1 || (vus.payPassingGrade ?: 0) <= payBall
            val matchesPayPlace = payPlace == -1 || (vus.payPlace ?: 0) >= payPlace
            val matchesCost = cost == -1 || (vus.cost ?: 0) <= cost
            val matchesCourse = course == -1 || (vus.introCoursesPrice ?: 0) <= course
            val matchesCity = city.isEmpty() || vus.city.contains(city, ignoreCase = true)
            val matchesWarCaf = vus.isMilitary == warCaf


            matchesName && matchesBudgBall && matchesBudgPlace && matchesPayBall && matchesPayPlace && matchesCity && matchesCourse && matchesCost && matchesWarCaf && matchPrograms
        }
    }

}


