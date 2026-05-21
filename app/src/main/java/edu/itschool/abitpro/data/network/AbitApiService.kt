package edu.itschool.abitpro.data.network

import edu.itschool.abitpro.data.dto.HeiDto
import retrofit2.http.GET

interface AbitApiService {
    @GET("universities")
    suspend fun getUniversities(): List<HeiDto>
}
