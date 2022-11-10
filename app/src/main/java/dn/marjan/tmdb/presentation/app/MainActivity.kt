package dn.marjan.tmdb.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dn.marjan.tmdb.ui.theme.TMDBTheme


@AndroidEntryPoint
class MainActivity() : ComponentActivity() {

    private  val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.getPopularMovies()

        setContent {
            TMDBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("World"){
                        mainViewModel.getPopularMovies()
                    }
                }
            }
        }

    }

}


@Composable
fun Greeting(name: String = "empty", onClick: ()-> Unit) {

    Text(text = "Hello $name!" , modifier = Modifier.clickable {
     onClick()
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TMDBTheme {
//        Greeting("Android")
    }
}