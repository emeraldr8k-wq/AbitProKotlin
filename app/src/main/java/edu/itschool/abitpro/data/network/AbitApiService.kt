package edu.itschool.abitpro.data.network

import edu.itschool.abitpro.data.dto.HeiDto
import edu.itschool.abitpro.domain.model.Hei
import retrofit2.http.GET

interface AbitApiService {
    @GET("universities")
    suspend fun getUniversities(): List<HeiDto>

    @GET("universities/search")
    suspend fun searchUniversities(@retrofit2.http.Query("name") name: String): List<HeiDto>

    @GET("universities/{id}")
    suspend fun getUniversityById(@retrofit2.http.Path("id") id: Long): HeiDto
}