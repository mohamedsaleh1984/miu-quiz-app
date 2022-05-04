package com.miu.quizapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

//Entity Class
@Entity(tableName = "question_table")
data class Question(
    val question: String,
    val questionChoices:String,
    val correctAnswer: Int,
    var answer:Int
)
{
    @PrimaryKey(autoGenerate = true) var QId:Int? = null
}