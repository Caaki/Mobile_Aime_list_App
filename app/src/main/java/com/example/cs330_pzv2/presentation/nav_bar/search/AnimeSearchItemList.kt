package com.example.cs330_pzv2.presentation.nav_bar.search


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cs330_pzv2.domain.model.Anime
import com.example.cs330_pzv2.presentation.Screen
import com.example.cs330_pzv2.presentation.anime_main_page.find_anime.AnimeMainPageVIewModel
import com.example.cs330_pzv2.presentation.anime_main_page.find_anime.components.AnimeMainItem

@Composable
fun AnimeSearchItemList(
    animeList: List<Anime>,
    title: String,
    viewModel:AnimeMainPageVIewModel = hiltViewModel(),
    navController: NavController
) {

    var currentPage by remember { mutableStateOf(1) }

    Text(
        text = title + " anime",
        fontSize = 35.sp,
        modifier = Modifier.padding(
            top = 10.dp,
            start = 32.dp
        ),
        fontFamily = FontFamily.Monospace
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .width(360.dp)
                .padding(16.dp)
        ) {
            items(animeList) { anime ->
                AnimeMainItem(
                    anime = anime,
                    onItemClick = {
                        navController.navigate(Screen.AnimeDetailsScreen.route+ "/${anime.id}")
                    },
                    modifier = Modifier.padding(16.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {


                    IconButton(
                        modifier = Modifier
                            .scale(1.5f),
                        onClick = {
                            if (currentPage > 0) {
                                --currentPage
                            }
                            viewModel.searchAnime(page = currentPage)
                        },
                        enabled = currentPage > 1
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Green
                        )
                    }

                    Text(text = "Page ${currentPage} of ${viewModel.state.value.searchCount/10 + 1}",
                    modifier = Modifier.padding(top =  12.dp , start = 4.dp, end = 4.dp))

                    IconButton(
                        modifier = Modifier
                            .scale(1.5f),
                        onClick = {
                            if (currentPage < viewModel.state.value.searchCount/10 + 1) {
                                ++currentPage
                            }
                            viewModel.searchAnime(page = currentPage)
                        },
                        enabled = currentPage < (viewModel.state.value.searchCount/10 + 1)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Forward",
                            tint = Color.Green
                        )
                    }

                }
            }
        }
    }
}


