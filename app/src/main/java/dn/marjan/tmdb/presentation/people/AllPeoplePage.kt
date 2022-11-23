package dn.marjan.tmdb.presentation.people

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dagger.hilt.android.AndroidEntryPoint
import dn.marjan.tmdb.app.constants.Constant
import dn.marjan.tmdb.presentation.people.components.AllPeopleState
import dn.marjan.tmdb.presentation.people.viewmodel.AllPeopleViewModel
import dn.marjan.tmdb.presentation.shared_components.LandPeopleItemComponent
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AllPeoplePage : ComponentActivity() {

    private val allPeopleViewModel: AllPeopleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pageTitle: String = intent?.getStringExtra(Constant.PAGE_TITLE_KEY) ?: ""
        allPeopleViewModel.getBodyData()


        setContent {
            BodyContent(
                pageTitle,
                allPeopleViewModel
            ) { onBackPressedDispatcher.onBackPressed() }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(pageTitle: String, viewModel: AllPeopleViewModel, onBackPressed: () -> Unit) {
    val peoplePager = viewModel.peoplePager.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = pageTitle) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(peoplePager) { item ->
                item?.let {
                    LandPeopleItemComponent(
                        photo = item.profilePath ?: "",
                        name = item.name,
                        popularity = item.popularity.toString(),
                        knownFor = item.knownFor
                    )
                }

            }
        }
    }
}

