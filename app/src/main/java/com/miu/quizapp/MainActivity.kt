package com.miu.quizapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // Declaration of ViewModel Object
    var mainViewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the ViewModel to retrieve the data from the ViewModel
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // Initially load zero to score field
        tvScorePlayerA.text = mainViewModel!!.getInitialCountA().toString()
        tvScorePlayerB.text = mainViewModel!!.getInitialCountB().toString()
        // Click Listener to increment the playerA Score by 1 for each click and updated on the ScoreTextView
        btnPlayerA.setOnClickListener{
            tvScorePlayerA.text = mainViewModel!!.currentCountA.toString()
        }
        // Click Listener to increment the playerB Score by 1 for each click and updated on the ScoreTextView
        btnPlayerB.setOnClickListener{
            tvScorePlayerB.text = mainViewModel!!.currentCountB.toString()
        }

    }
}