package dn.marjan.tmdb.presentation.people.details.components

import dn.marjan.tmdb.domain.entity.*

data class PersonDetailsState(
    val isLoading: Boolean = false,
    val people: People? = null ,
    val hasError: Boolean = false
)
data class PersonPicturesState(
    val isLoading: Boolean = false,
    val  peoplePictures: List<String> = emptyList(),
)