package edu.itschool.abitpro.domain.model

data class Hei(
    val id: Int,
    val name: String,
    val city: String = "",
    val description: String = "",
    val web: String = "",
    val programs: List<String> = emptyList(),
    val freePassingGrade: Int = -1,
    val rating: Int = -1,
    val freePlace: Int = -1,
    val payPassingGrade: Int = -1,
    val payPlace: Int = -1,
    val cost: Int = -1,
    val introCoursesPrice: Int = -1
)