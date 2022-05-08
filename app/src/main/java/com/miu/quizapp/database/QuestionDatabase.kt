package com.miu.quizapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class  QuestionDatabase():RoomDatabase() {
    abstract fun questionDao() : QuestionDao
    // Build RoomDB
    /*
    companion object {
        //  meaning that writes to this field are immediately made visible to other threads
        @Volatile private var instance : QuestionDatabase? = null
        private val LOCK = Any() // The root of the Kotlin class hierarchy. Every Kotlin class has [Any] as a superclass.
        //  Invoke check if the instance is not null return the instance, if it is null
        //  synchronized block work, inside this also check null or not and call the function buildDatabase
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        // Function to build database
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            QuestionDatabase::class.java,
            "question_database"
        ).build()
    }
*/
}