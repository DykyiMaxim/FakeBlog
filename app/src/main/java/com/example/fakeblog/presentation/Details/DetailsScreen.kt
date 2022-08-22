package com.example.fakeblog.presentation.Details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fakeblog.presentation.Home.PostItem
import com.google.accompanist.flowlayout.FlowRow
import com.example.fakeblog.R

@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel = hiltViewModel()) {

    val res = detailsViewModel.details.value


    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error.toString(), modifier = Modifier.align(Alignment.Center))
        }
    }
    res.data?.let {
        Column(modifier = Modifier) {
            PostItem(it = it, I = {})
            Text(text = it.likes.toString()+" "+ stringResource(id = R.string.emoji), modifier = Modifier.padding(18.dp))
            FlowRow {
                it.tags.forEach {
                    TagItem(it = it)
                }
            }

        }


    }


}


@Composable
fun TagItem(it: String) {

    Card(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp), shape = RoundedCornerShape(
            40.dp
        ),
        border = BorderStroke(0.5.dp, color = Color.Cyan),
        backgroundColor = Color.Cyan
    ) {
        Text(text = it, style = TextStyle(color = Color.Black), modifier = Modifier.padding(12.dp))
    }

}
