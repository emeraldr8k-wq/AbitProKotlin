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
        programs = emptyList(),
        freePassingGrade = budgBall,
        freePlace = budgPlace,
        payPassingGrade = paidBall,
        payPlace = paidPlace,
        cost = -1
    )
}
