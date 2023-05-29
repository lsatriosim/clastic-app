package com.example.clastic.ui.screen

sealed class Screen(val route:String) {

    object login: Screen("login")
    object register: Screen("register")
    object articleDetail: Screen("articleDetail/{articleUrl}"){
        fun createRoute(articleUrl: String) = "articleDetail/$articleUrl"
    }
    object articleList: Screen("article")

    object Home: Screen("home")

    object ProductKnowledge: Screen("productKnowledge/{tag}"){
        fun createRoute(tag: String) = "productKnowledge/$tag"
    }
}