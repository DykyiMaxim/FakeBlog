package com.example.fakeblog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fakeblog.navigation.NavigationItem
import com.example.fakeblog.presentation.Home.HomeScreen
import com.example.fakeblog.ui.theme.FakeBlogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeBlogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController:NavController = rememberNavController()
                    app {
                        NavHost(
                            navController = navController as NavHostController,
                            startDestination = NavigationItem.Home.route
                        ){
                            composable(NavigationItem.Home.route){
                                HomeScreen()
                            }

                        }

                    }
                }
            }
        }
    }
}
@Composable
fun app(content:@Composable ()->Unit){
    content()
}