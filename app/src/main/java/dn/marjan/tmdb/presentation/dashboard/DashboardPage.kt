package dn.marjan.tmdb.presentation.dashboard

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.*
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.entity.Tvshow
import dn.marjan.tmdb.presentation.dashboard.components.*
import dn.marjan.tmdb.presentation.dashboard.viewmodel.DashboardViewModel
import dn.marjan.tmdb.presentation.shared_components.LoadingComponent
import dn.marjan.tmdb.presentation.shared_components.SpacerComponent


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DashboardPage(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    Log.d("MARJAN", "DashboardPage RECOMPOSE!")


    Column(
        modifier = Modifier.verticalScroll(state = rememberScrollState())
    ) {
        PopularMoviesList(viewModel.popularMovies)
        SpacerComponent(height = 24.dp)
        FeaturedMoviesList(viewModel.featuredMovies)
        SpacerComponent(height = 24.dp)
        UpcomingMoviesList(viewModel.upComingMovies)
        SpacerComponent(height = 24.dp)
        PopularPeopleList(viewModel.popularPeople)
        SpacerComponent(height = 24.dp)
        PopularTvshowList(viewModel.popoularTvshows)
        SpacerComponent(height = 24.dp)
        FeaturedTvshowList(viewModel.featuredTvshow)
    }

}

@Composable
fun PopularMoviesList(list: List<Movie>) {
    HomeListHeader("Today Popular Movies") {}
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.title,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                )
            }
        }
    }
}


@Composable
fun PopularPeopleList(list: List<People>) {
    HomeListHeader("Today Popular People") {}
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items = list) { item ->
                PeopleItem(item)
            }
        }
    }

}


@Composable
fun UpcomingMoviesList(list: List<Movie>) {
    HomeListHeader("Upcoming Movies") {}
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.title,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                )
            }
        }
    }

}

@Composable
fun FeaturedMoviesList(list: List<Movie>) {
    HomeListHeader("Featured Movies") {}
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.title,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                )
            }
        }
    }

}


@Composable
fun PopularTvshowList(list: List<Tvshow>) {
    HomeListHeader("Popular Tv Shows") {}
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.name,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                )
            }
        }
    }
}


@Composable
fun FeaturedTvshowList(list: List<Tvshow>) {
    HomeListHeader("Featured TV Shows") {}
    SpacerComponent(height = 16.dp)
    if (list.isEmpty()) {
        LoadingComponent()
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items = list) { movie ->
                FilmItem(
                    title = movie.name,
                    coverPhoto = movie.posterPath,
                    voteAverage = movie.voteAverage.toString()
                )
            }
        }
    }
}