package dn.marjan.tmdb.presentation.dashboard

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.*
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import dn.marjan.tmdb.app.constants.Constant
import dn.marjan.tmdb.app.constants.MoviesType
import dn.marjan.tmdb.app.constants.TvshowType
import dn.marjan.tmdb.app.extensions.navigation
import dn.marjan.tmdb.app.extensions.putEnumExtra
import dn.marjan.tmdb.domain.entity.Movie
import dn.marjan.tmdb.domain.entity.People
import dn.marjan.tmdb.domain.entity.Tvshow
import dn.marjan.tmdb.presentation.dashboard.components.*
import dn.marjan.tmdb.presentation.dashboard.viewmodel.DashboardViewModel
import dn.marjan.tmdb.presentation.movies.AllMoviesPage
import dn.marjan.tmdb.presentation.people.AllPeoplePage
import dn.marjan.tmdb.presentation.shared_components.LoadingComponent
import dn.marjan.tmdb.presentation.shared_components.SpacerComponent
import dn.marjan.tmdb.presentation.tvshows.AllTvshowPage


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DashboardPage(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.verticalScroll(state = rememberScrollState())
    ) {
        PopularMoviesList(viewModel.popularMovies) {
            context.startActivity(
                context.navigation<AllMoviesPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Today Popular Movies"
                    )
                ).putEnumExtra<MoviesType>(enum = MoviesType.populraMovies)
            )
        }
        SpacerComponent(height = 24.dp)
        FeaturedMoviesList(viewModel.featuredMovies) {
            context.startActivity(
                context.navigation<AllMoviesPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Featured Movies"
                    )
                ).putEnumExtra<MoviesType>(enum = MoviesType.featuredMovies)
            )
        }
        SpacerComponent(height = 24.dp)
        UpcomingMoviesList(viewModel.upComingMovies) {
            context.startActivity(
                context.navigation<AllMoviesPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Upcoming Movies"
                    )
                ).putEnumExtra<MoviesType>(enum = MoviesType.upcomingMovies)
            )
        }
        SpacerComponent(height = 24.dp)
        PopularPeopleList(viewModel.popularPeople) {
            context.startActivity(
                context.navigation<AllPeoplePage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Today Popular People"
                    )
                )
            )
        }
        SpacerComponent(height = 24.dp)
        PopularTvshowList(viewModel.popoularTvshows) {
            context.startActivity(
                context.navigation<AllTvshowPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Today Popular Tv shows"
                    )
                ).putEnumExtra<TvshowType>(enum = TvshowType.populraTvshows)
            )
        }
        SpacerComponent(height = 24.dp)
        FeaturedTvshowList(viewModel.featuredTvshow) {
            context.startActivity(
                context.navigation<AllTvshowPage>(
                    extras = mapOf<String, String>(
                        Constant.PAGE_TITLE_KEY to "Featured Tv shows"
                    )
                ).putEnumExtra<TvshowType>(enum = TvshowType.featuredTvshows)
            )
        }
    }

}



@Composable
fun PopularMoviesList(list: List<Movie>, showAllTapped: () -> Unit) {

    HomeListHeader("Today Popular Movies") {
        showAllTapped()
    }
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
fun PopularPeopleList(list: List<People>, showAllTapped: () -> Unit) {
    HomeListHeader("Today Popular People") {
        showAllTapped()
    }
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
fun UpcomingMoviesList(list: List<Movie>, showAllTapped: () -> Unit) {
    HomeListHeader("Upcoming Movies") { showAllTapped() }
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
fun FeaturedMoviesList(list: List<Movie>, showAllTapped: () -> Unit) {
    HomeListHeader("Featured Movies") { showAllTapped() }
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
fun PopularTvshowList(list: List<Tvshow>, showAllTapped: () -> Unit) {
    HomeListHeader("Popular Tv Shows") { showAllTapped() }
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
fun FeaturedTvshowList(list: List<Tvshow>, showAllTapped: () -> Unit) {
    HomeListHeader("Featured TV Shows") { showAllTapped() }
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