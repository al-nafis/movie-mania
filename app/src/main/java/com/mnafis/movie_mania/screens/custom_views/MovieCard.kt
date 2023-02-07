package com.mnafis.movie_mania.screens.custom_views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mnafis.movie_mania.R
import com.mnafis.movie_mania.theme.Dimens
import com.mnafis.movie_mania.theme.Typography

@Composable
fun MovieCard(
    title: String,
    year: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(Dimens.thumbnailImageHeight)
            .clickable(onClick = onClick)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth(Dimens.thumbnailImageWidth)
                    .fillMaxHeight()
            ) {
                if (imageUrl.lowercase() == "n/a") {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.poster_place_holder),
                        contentDescription = stringResource(id = R.string.movie_mania_add_movie_details_movie_poster_place_holder_content_description)
                    )
                } else {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        model = imageUrl,
                        contentDescription = stringResource(id = R.string.movie_mania_add_movie_card_item_image_content_description)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = Dimens.paddingMd),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.headlineLarge
                )
                Text(
                    text = stringResource(
                        id = R.string.movie_mania_add_movie_card_item_release_year,
                        year
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewMovieCard() {
    MovieCard("Movie Name", "2023", "url") {}
}