package edu.itschool.abitpro.data.dto

data class HeiDto (
    val id : Int,
    val name : String = "",
    val city : String = "",
    val description : String = "",
    val officialWebsite : String = "" ,
    val rating : Int = -1,
    val military : Boolean = false,
    val militaryFromCourse : Int = -1
): java.io.Serializable