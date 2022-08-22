package com.example.fakeblog.navigation

sealed class NavigationItem(val route:String) {
    object Home:NavigationItem("Home")
    object DetailsScreen : NavigationItem("details/{blogId}")

}