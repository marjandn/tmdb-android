package dn.marjan.tmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dn.marjan.tmdb.domain.repository.MovieRepository
import dn.marjan.tmdb.domain.repository.PeopleRepository
import dn.marjan.tmdb.domain.repository.TvshowRepository
import dn.marjan.tmdb.domain.usecase.movie.GetFeaturedMovies
import dn.marjan.tmdb.domain.usecase.movie.GetPopularMovies
import dn.marjan.tmdb.domain.usecase.movie.SearchMovie
import dn.marjan.tmdb.domain.usecase.people.GetPopularPeople
import dn.marjan.tmdb.domain.usecase.people.SearchPeople
import dn.marjan.tmdb.domain.usecase.tvshow.GetFeaturedTvshows
import dn.marjan.tmdb.domain.usecase.tvshow.GetPopularTvshows
import dn.marjan.tmdb.domain.usecase.tvshow.SearchTvshow


@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetPopularMovies(movieRepository: MovieRepository): GetPopularMovies =
        GetPopularMovies(movieRepository)

    @Provides
    fun provideGetFeaturedMovies(movieRepository: MovieRepository): GetFeaturedMovies =
        GetFeaturedMovies(movieRepository)

    @Provides
    fun provideSearchMovies(movieRepository: MovieRepository): SearchMovie =
        SearchMovie(movieRepository)

    @Provides
    fun provideGetPopularTvshows(tvshowRepository: TvshowRepository): GetPopularTvshows =
        GetPopularTvshows(tvshowRepository)

    @Provides
    fun provideGetFeaturedTvshows(tvshowRepository: TvshowRepository): GetFeaturedTvshows =
        GetFeaturedTvshows(tvshowRepository)

    @Provides
    fun provideSearchTvshow(tvshowRepository: TvshowRepository): SearchTvshow =
        SearchTvshow(tvshowRepository)

    @Provides
    fun provideGetFeaturedPeople(peopleRepository: PeopleRepository): GetPopularPeople =
        GetPopularPeople(peopleRepository)

    @Provides
    fun provideSearchPeople(peopleRepository: PeopleRepository): SearchPeople =
        SearchPeople(peopleRepository)

}