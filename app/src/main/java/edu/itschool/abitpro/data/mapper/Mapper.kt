package edu.itschool.abitpro.data.mapper

import edu.itschool.abitpro.data.dto.HeiDto
import edu.itschool.abitpro.domain.model.Hei

fun HeiDto.toHei(): Hei {
    return Hei(
        id = id.toInt(),
        name = name,
        city = city,
        description = description,
        web = officialWebsite,
        rating = -1,
        programs = popularPrograms?.split(",")?.map { it.trim() } ?: emptyList(),
        isMilitary = isMilitary,
        militaryFromCourse = militaryFromCourse,
        freePassingGrade = budgBall,
        freePlace = budgPlace,
        payPassingGrade = paidBall,
        payPlace = paidPlace,
        cost = paidCost,
        introCoursesPrice = introCoursesPrice
    )
}

fun List<HeiDto>.toHeiList(): List<Hei> {
    return this.map { it.toHei() }
}