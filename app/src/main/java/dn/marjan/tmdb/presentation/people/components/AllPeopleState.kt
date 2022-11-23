package dn.marjan.tmdb.presentation.people.components

import androidx.annotation.StringRes
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.entity.People


data class AllPeopleState(
    val isLoading: Boolean = false,
    val people: List<People> = emptyList(),
    val errorMessage: String = ""
)