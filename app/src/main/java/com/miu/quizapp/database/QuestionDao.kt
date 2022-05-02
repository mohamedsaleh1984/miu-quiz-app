package com.miu.quizapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.jetbrains.annotations.NotNull

//Data Access Object
@Dao
interface QuestionDao {
    @Insert
    suspend fun addQuestion(Q: Question)
    @Update
    suspend fun resetQuestionsAnswers(QuestionList: List<Question>)
//    @Query("SELECT * FROM Question")
//    suspend fun getAllQuestions(): List<Question>
//    @Insert
   // suspend fun addMultipleQuestions(vararg  QuestionList:Question)

}

/*  @Query("SELECT * FROM Question")
  suspend fun getAllQuestions(): List<Question>
/@Insert
 suspend fun addMultipleQuestions(vararg  QuestionList:Question)
/   @Update
 @NotNull
 suspend fun resetQuestionsAnswers(QuestionList: List<Question>)

 @Update
 @NotNull
 suspend fun updateQuestionAnswer(Q: Question)

} */