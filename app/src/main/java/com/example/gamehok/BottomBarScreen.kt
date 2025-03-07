package com.example.gamehok

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    object Home : BottomBarScreen(
        "home",
        title = "Home",
        icon = R.drawable.home,
        icon_focused = R.drawable.home
    )

    object Tournament: BottomBarScreen(
        route = "tournament",
        title = "My Tournament",
        icon = R.drawable.my_tournament,
        icon_focused = R.drawable.my_tournament
    )

    object Social: BottomBarScreen(
        route = "social",
        title = "Social",
        icon = R.drawable.social,
        icon_focused = R.drawable.social
    )

    object Chats: BottomBarScreen(
        route = "chats",
        title = "Chats",
        icon = R.drawable.chats,
        icon_focused = R.drawable.chats
    )

}