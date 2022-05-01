package com.miu.quizapp.data

data class Question(
    val correctAnswer: Int,
    val id: String,
    val question: String,
    val questionChoices: List<String>,
    val answer:Int
)