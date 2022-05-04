package com.miu.quizapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

//Data Access Object
@Dao
interface QuestionDao {
    @Update
    fun UpdateQuestion(Q:Question)

    @Query("Update question_table Set answer = -1")
    fun ResetAllQuestionsAnswers()

    @Query("SELECT * FROM question_table")
    fun GetAllQuestions(): List<Question>

    @Insert
    fun InsertQuestion(vararg question:Question)

    @Query("Select Count(*) From question_table")
    fun RowsCount():Int

    @Query("Delete From question_table")
    fun DeleteAll()
}
