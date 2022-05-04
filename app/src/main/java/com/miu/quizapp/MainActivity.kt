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

      var q1 = Question("Whats best programming language for system xxxx?",
          "C++|Assembly|Java",
            0,
            -1);
        var q2 = Question("Whats best programming language for system zzzz?",
            "C++|Assembly|Java",
            1,
            -1);
        var q3 = Question("Whats best programming language for system yyyy?",
            "C++|Assembly|Java",
            3,
            -1);
        db.questionDao().InsertQuestion(q1,q2,q3);
    }

    fun onStartClick(view: View){
        val i = Intent(applicationContext, QuestionActivity::class.java)
        startActivity(i)
    }
}


