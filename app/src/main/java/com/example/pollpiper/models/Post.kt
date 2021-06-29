package com.example.pollpiper.models

data class Post (
    val text: String = "",
//    val createdBy: User = User(),
    val createdBy: String = "",
    val createdAt: Long = 0L,
    val option1: String = "",
    val option2: String = "",
    val option3: String = "",
    val option4: String = "",
    val option1count: ArrayList<String> = ArrayList(),
    val option2count: ArrayList<String> = ArrayList(),
    val option3count: ArrayList<String> = ArrayList(),
    val option4count: ArrayList<String> = ArrayList())