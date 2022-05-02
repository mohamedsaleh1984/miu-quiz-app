package com.miu.quizapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

//Entity Class
@Entity(tableName = "question_table")
class Question(
    @PrimaryKey
    @NotNull
    val id: Int,
    val correctAnswer: Int,
    val question: String,
    val questionChoices:String,
    val answer:Int
)
