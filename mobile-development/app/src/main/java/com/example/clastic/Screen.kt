package com.example.clastic

sealed class Screen(val route:String) {
    object articleDetail:Screen("articleDetail/{articleUrl}"){
        fun createRoute(articleUrl: String) = "articleDetail/$articleUrl"
    }
    object articleList:Screen("article")
}