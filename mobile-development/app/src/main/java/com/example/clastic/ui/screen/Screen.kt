package com.example.clastic.ui.screen

sealed class Screen(val route:String) {

    object splashScreen: Screen("splashScreen")
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

    object profile: Screen("profile")

    object myQRCode: Screen("myQRCode")
    object qrCodeScanner: Screen("qrCodeScanner")
    object createTransaction: Screen("createTransaction/{scannedUID}") {
        fun createRoute(scannedUID: String) = "createTransaction/$scannedUID"
    }

    object transactionCreated: Screen("transactionCreated/{transactionId}") {
        fun createRoute(transactionId: String) = "transactionCreated/$transactionId"
    }
}