package com.example.fakeblog.presentation.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Cyan)
            
        }
    }
    if(homeState.error.isNotBlank()){
        Box(modifier = Modifier.fillMaxSize()){
            Text(
                text = homeState.error,
                modifier = Modifier.align(Alignment.Center))
        }
    }
    LazyColumn(Modifier.background(Color.LightGray)){
        homeState.data?.let{
            items(it){
                PostItem(it)
            }
        }
    }
}

@Composable
fun PostItem(it:Blog){
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(8.dp)
    ) {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center)
    {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        )
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
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = rememberImagePainter(data = it.image), contentDescription = null,
            contentScale = ContentScale.Crop,

        )
        Text(
            text = it.text,
            modifier = Modifier.padding(12.dp),
            style = TextStyle(color = Color.Gray, fontSize = 20.sp)
        )
        Divider()
    }
        
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
