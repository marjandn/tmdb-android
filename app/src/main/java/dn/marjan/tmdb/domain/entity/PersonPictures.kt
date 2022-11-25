package dn.marjan.tmdb.domain.entity

import dn.marjan.tmdb.app.constants.Constant

data class PersonPictures(
    private  val _picturePath: String
) {
    val picturePath get() = "${Constant.IMAGE_ADDRESS}$_picturePath"
}