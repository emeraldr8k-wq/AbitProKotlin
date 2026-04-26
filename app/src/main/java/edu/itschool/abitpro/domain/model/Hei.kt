package edu.itschool.abitpro.domain.model

data class Hei(
    val id: Int,   //под вопросом, для навигации сделано String
    val name: String,
    val city: String = "",
    val description: String = "",
    val web: String = "",
    val programs: List<String> = emptyList(),
    val freePassingGrade: Int = -1,
    val freePlace: Int = -1,
    val payPassingGrade: Int = -1,
    val payPlace: Int = -1,
    val cost: Int = -1
)