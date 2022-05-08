package com.miu.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.room.Room
import com.miu.quizapp.database.Question
import com.miu.quizapp.database.QuestionDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var  db:QuestionDatabase;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(applicationContext, QuestionDatabase::class.java,"question-db").build();

        GlobalScope.launch {
            db.questionDao().DeleteAll();
            DataSeed()
        }
    }

    private suspend fun DataSeed(){

      var q1 = Question("1. Who developed Kotlin?",
          "(A) IBM|(B) Google|(C) JetBrains",
            2,
            -1);
        var q2 = Question("2. Which extension is responsible to save Kotlin files ?",
            "(A) .android|(B) .src|(C) .kt or .kts",
            2,
            -1);
        var q3 = Question("3. How to do a multi-line comment in Kotlin language ?",
            "(A) //|(B) /**/|(C) \\\\",
            1,
            -1);


        var q4 = Question("4. Kotlin only works for supporting Java language ?",
            "(A) Yes|(B) No|(C) Neither",
            1,
            -1);

        var q5 = Question("5. The two types of constructors in Kotlin are ?",
            "(A) Primary and Secondary constructor|(B) First and the second constructor|(C) Constant and Parameterized constructor",
            0,
            -1);

        var q6 = Question("6. What handles null exceptions in Kotlin ?",
            "(A) Sealed classes|(B) Elvis operator|(C) The Kotlin extension",
            1,
            -1);

        var q7 = Question("7. The correct function to get the length of a string in Kotlin language is ?",
            "(A) str.length|(B) string(length)|(C) lengthof(str)",
            0,
            -1);

        var q8 = Question("8. The function to print a line in Kotlin is ?",
            "(A) Printline()|(B) println()|(B) WriteLine()",
            1,
            -1);

        var q9 = Question("9. Under which license Kotlin was developed ?",
            "(A) 1.1|(B) 1.5|(C) 2.0",
            1,
            -1);

        var q10 = Question("10. The functions in Kotlin can be divided into how many types ?",
            "(A) 5|(B) 3|(C) 2",
            2,
            -1);

        var q11 = Question("11. In Kotlin, the default visibility modifier is ?",
            "(A) sealed|(B) public|(C) protected",
            1,
            -1);
        var q12 = Question("12. What defines a sealed class in Kotlin ?",
            "(A) Its another name for an abstract class|(B) It represents restricted class hierarchies|(C) It is used in every Kotlin program",
            1,
            -1);
        var q13 = Question("13. Which are the basic data types in Kotlin ?",
            "(A) Arrays and Booleans|(B) Characters|(C) All of these",
            2,
            -1);
        var q14 = Question("14. Which of these features are available in Kotlin but not in the Java language ?",
            "(A) Operator overloading|(B) Coroutines and Null safety|(C) All of the above",
            2,
            -1);
        var q15 = Question("15. val short for Value, a constant which cannot be changed once assigned ?",
            "(A) It can be changed but once only|(B) It can be changed|(C) val cannot be changed after its assigned",
            2,
            -1);

        db.questionDao().InsertQuestion(q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15);
    }

    fun onStartClick(view: View){
        val i = Intent(applicationContext, QuestionActivity::class.java)
        startActivity(i)
    }

    fun onExitClick(view: View){
       finish()
    }

    override fun onBackPressed() {
        finish()
    }
}


