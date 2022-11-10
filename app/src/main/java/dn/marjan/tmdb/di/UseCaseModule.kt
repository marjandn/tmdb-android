package dn.marjan.tmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dn.marjan.tmdb.domain.repository.MovieRepository
import dn.marjan.tmdb.domain.usecase.GetPopularMovies


@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideTempUseCase(movieRepository: MovieRepository): GetPopularMovies = GetPopularMovies(movieRepository)

}