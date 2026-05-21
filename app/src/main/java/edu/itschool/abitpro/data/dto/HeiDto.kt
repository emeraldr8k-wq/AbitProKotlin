package edu.itschool.abitpro.data.dto

import com.google.gson.annotations.SerializedName

data class HeiDto(
    val id: Long,
    val name: String,
    val city: String,
    val description: String,
    val officialWebsite: String,
    val rating: Int,
    val isMilitary: Boolean,
    val militaryFromCourse: Int,

    val budgBall: Int = -1,
    val budgPlace: Int = -1,
    val paidBall: Int = -1,
    val paidPlace: Int = -1
)