package com.miu.quizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.miu.quizapp.data.Question

class QuestionFragment : Fragment() {

    lateinit var radioGroup:RadioGroup
    private var selectedAnswer:Int = -1;
    lateinit var question:Question;
    private var questionId:Int = 0;
    private lateinit var questList:List<Question>;

    lateinit var tvQuestion: TextView
    lateinit var rdBtn1: RadioButton
    lateinit var rdBtn2: RadioButton
    lateinit var rdBtn3: RadioButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioGroup.clearCheck();

        tvQuestion = view.findViewById(R.id.tvQuestion);
        radioGroup = view.findViewById(R.id.groupradio);
        rdBtn1  = view.findViewById(R.id.radia_id1);
        rdBtn2  = view.findViewById(R.id.radia_id2);
        rdBtn3  = view.findViewById(R.id.radia_id3);

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = group.findViewById<View>(checkedId) as RadioButton
        }
    }

    fun onNextClick(view:View){
        val selectedId = radioGroup.checkedRadioButtonId
        question.answer=selectedAnswer;
    }

    fun onSkipClick(view:View){
        radioGroup.clearCheck();
        //Move to next Question
        questionId++;
        if(questionId == 16){
            //move to result fragment
        }
        else{
            //render next question
            renderQuestion(questList[questionId])
        }
    }
    fun renderQuestion(Q:Question){
        tvQuestion.setText(Q.question);
        rdBtn1.setText(Q.questionChoices[0]);
        rdBtn2.setText(Q.questionChoices[1]);
        rdBtn3.setText(Q.questionChoices[2]);

    }

    //TODO: Fetch All Questions
    //TODO: Render
    //TODO: Next
    //TODO:

}