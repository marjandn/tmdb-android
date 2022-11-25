package dn.marjan.tmdb.domain.entity

import dn.marjan.tmdb.app.constants.Constant

data class MoviePictures(
    private val _posterPath: String,
) {
    val posterPath:String get()= "${Constant.IMAGE_ADDRESS}$_posterPath"
}