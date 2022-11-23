package dn.marjan.tmdb.domain.entity

import dn.marjan.tmdb.app.constants.Constant


data class People(
    val id: Int,
    val name: String,
    private val _profilePath: String?,
    val popularity: Double,
    val knownFor: String
) {
    val profilePath = _profilePath
        get() = "${Constant.IMAGE_ADDRESS}$field"
}