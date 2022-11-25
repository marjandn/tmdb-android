package dn.marjan.tmdb.data.model

import dn.marjan.tmdb.domain.entity.PersonPictures

class PersonPicturesResponse(
    val profiles: List<Profiles>
) {

    fun toEntity(): List<PersonPictures> = profiles.map {
        PersonPictures(it.file_path)
    }

    class Profiles(
        val file_path: String
    )
}