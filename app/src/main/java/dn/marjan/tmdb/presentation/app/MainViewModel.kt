package dn.marjan.tmdb.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dn.marjan.tmdb.app.base.usecase.NoParam
import dn.marjan.tmdb.data.datasources.remote.parameters.PagingParam
import dn.marjan.tmdb.domain.usecase.GetPopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private var getPopularMovies: GetPopularMovies) : ViewModel() {

  fun getPopularMovies(){

      viewModelScope.launch(Dispatchers.IO) {
          val response = getPopularMovies.invoke(PagingParam())

          println(response)
      }

  }
}
