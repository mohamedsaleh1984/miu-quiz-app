package com.miu.quizapp.data

data class Question(
    val correctAnswer: Int,
    val id: Int,
    val question: String,
    val questionChoices: List<String>,
    var answer:Int
)