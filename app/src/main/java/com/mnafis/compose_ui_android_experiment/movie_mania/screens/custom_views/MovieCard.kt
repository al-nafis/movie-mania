package com.mnafis.compose_ui_android_experiment.movie_mania.screens.custom_views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.ui.theme.ColorBlack
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens

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
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    alignment = Alignment.Center,
                    model = imageUrl,
                    contentDescription = stringResource(id = R.string.movie_mania_add_movie_card_item_image_content_description)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = Dimens.paddingMd),
                verticalArrangement = Arrangement.Center
            ) {
                Text(color = ColorBlack, text = title)
                Text(
                    color = ColorBlack,
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