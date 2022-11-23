package dn.marjan.tmdb.data.model

import com.squareup.moshi.Json
import dn.marjan.tmdb.domain.entity.People


class PeopleResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int


) {
    fun toEntity(): List<People> = results.map {
        People(
            it.id,
            it.name,
            it.profile_path,
            it.popularity,
            it.known_for_department ?:""
        )
    }
    class Result(
        val adult: Boolean,
        val id: Int,
        val known_for_department: String?,
        val name: String,
        val popularity: Double,
        val profile_path: String?,
    )
}

