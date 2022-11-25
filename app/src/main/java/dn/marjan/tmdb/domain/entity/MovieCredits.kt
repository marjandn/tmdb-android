package dn.marjan.tmdb.domain.entity

import dn.marjan.tmdb.app.constants.Constant

data class MovieCredits(
    private val _posterPath: String,
    val name: String,
    val knownFor:String
) {
    val posterPath: String get() = "${Constant.IMAGE_ADDRESS}$_posterPath"
}