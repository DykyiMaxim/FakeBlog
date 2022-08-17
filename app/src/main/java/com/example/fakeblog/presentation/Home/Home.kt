package com.example.fakeblog.presentation.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.domain.models.Blog

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel= hiltViewModel()
){

    val homeState = viewModel.BlogsState.value

    if(homeState.isLoading)
    {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
            
        }
    }
    if(homeState.error.isNotBlank()){
        Box(modifier = Modifier.fillMaxSize()){
            Text(
                text = homeState.error,
                modifier = Modifier.align(Alignment.Center))
        }
    }
    LazyColumn{
        homeState.data?.let{
            items(it){
                PostItem(it)
            }
        }
    }
}

@Composable
fun PostItem(it:Blog){
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center)
    {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically)
    {
        CircularImage(
            50.0,
            50.0,
            25.0,
            it.owner.picture
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "${it.owner.firstName} ${it.owner.lastName}")

    }
        Image(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            painter = rememberImagePainter(data = it.image), contentDescription = null,
            contentScale = ContentScale.Crop
            )
        
    }
}

@Composable
fun CircularImage(width:Double,height:Double,radius:Double,imageUrl:String){
    Card(modifier = Modifier
        .width(width = width.dp)
        .height(height = height.dp),
        shape = RoundedCornerShape(radius.dp)
    ) {
        Image(painter = rememberImagePainter(data = imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

}
