package com.example.clastic.data.entity

data class DropPoint(
    val id: String,
    val lat: Double,
    val long: Double,
    val name: String,
    val location: String,
    val ownerId: String
){
    constructor(): this("",0.0,0.0,"","","")
}