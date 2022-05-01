package com.miu.quizapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.miu.quizapp.data.Question
import kotlin.math.log

class QuestionFragment : Fragment() {
    private lateinit var radioGroup: RadioGroup
    private var selectedAnswer: Int = -1;
    private var question = Question (1,1,"Whats best programming language for system internals?", listOf("C++","Assembly","Java"),0);
    private var questionId: Int = 1;
    private lateinit var tvQuestion: TextView
    private lateinit var rdBtn1: RadioButton
    private lateinit var rdBtn2: RadioButton
    private lateinit var rdBtn3: RadioButton

    private lateinit var btnNext: Button
    private lateinit var btnSkip: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_question, container, false)

        tvQuestion = view.findViewById(R.id.tvQuestion);
        radioGroup = view.findViewById(R.id.groupradio);

        rdBtn1 = view.findViewById(R.id.radia_id1);
        rdBtn2 = view.findViewById(R.id.radia_id2);
        rdBtn3 = view.findViewById(R.id.radia_id3);

        btnNext = view.findViewById(R.id.btnNext);
        btnSkip = view.findViewById(R.id.btnSkip);

        renderQuestion(question)

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener({ onNextClick(view) });
        btnSkip.setOnClickListener({ onSkipClick(view) });

        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = group.findViewById<View>(checkedId) as RadioButton
            when (radioButton.id)
            {
                R.id.radia_id1 -> selectedAnswer = 0
                R.id.radia_id2 -> selectedAnswer = 1
                R.id.radia_id3 -> selectedAnswer = 2
                else -> selectedAnswer = -1
            }
        }
    }

    private fun onNextClick(view:View) {
        MoveToNextQuestion(view);
    }

    private fun onSkipClick(view:View) {
        radioGroup.clearCheck();
        MoveToNextQuestion(view);
    }

    private fun MoveToNextQuestion(view:View){
        ++questionId;
        if (questionId == 2) {
            //move to result fragment
            Log.i("TEST","Move to next fragment...");
            Navigation.findNavController(view).navigate(R.id.resultFragment);
        } else {
            //render next question
            var q = Question (0,1,"sssss", listOf("Step1","Step2","Step3"),0);
            renderQuestion(q)
            //renderQuestion(questList[questionId])
        }
    }

    private fun renderQuestion(Q: Question) {
        tvQuestion.setText(Q.question);
        rdBtn1.setText(Q.questionChoices[0]);
        rdBtn2.setText(Q.questionChoices[1]);
        rdBtn3.setText(Q.questionChoices[2]);
    }
}