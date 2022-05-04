package com.miu.quizapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.room.Room
import com.miu.quizapp.database.Question
import com.miu.quizapp.database.QuestionDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuestionFragment : BaseFragment() {
    private lateinit var radioGroup: RadioGroup

    private var selectedAnswer: Int = -1;
    private var questionIndex: Int = 0;

    private lateinit var tvQuestion: TextView
    private lateinit var rdBtn1: RadioButton
    private lateinit var rdBtn2: RadioButton
    private lateinit var rdBtn3: RadioButton

    private lateinit var btnNext: Button
    private lateinit var btnSkip: Button

    lateinit var  db:QuestionDatabase;
    lateinit var qlist:List<Question>;

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

        db = Room.databaseBuilder(requireContext(), QuestionDatabase::class.java,"question-db").build();
        GlobalScope.launch {
            qlist = db.questionDao().GetAllQuestions();
            FetchQuestion(questionIndex);
            Log.i("TEST","Loaded...")
        }

       // renderQuestion(question)
        return view;
    }

    private  suspend fun FetchQuestion(index:Int){
        Log.i("TEST","FetchQuestion...")
        var q =  qlist[index]
        RenderQuestion(q)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener({ onNextClick(view) });
        btnSkip.setOnClickListener({ onSkipClick(view) });

        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = group.findViewById<View>(checkedId) as RadioButton
            when (radioButton.id) {
                R.id.radia_id1 -> selectedAnswer = 0
                R.id.radia_id2 -> selectedAnswer = 1
                R.id.radia_id3 -> selectedAnswer = 2
                else -> selectedAnswer = -1
            }
            Toast.makeText(requireContext(),selectedAnswer.toString(),Toast.LENGTH_LONG).show();
            Log.i("TEST","Question Answer Updated...")
            UpdateQuestionAnswer(questionIndex,selectedAnswer);
        }
    }

    private fun onNextClick(view: View) {
        MoveToNextQuestion(view);
    }

    private fun onSkipClick(view: View) {
        radioGroup.clearCheck();
        MoveToNextQuestion(view);
    }

    private fun MoveToNextQuestion(view: View) {
        radioGroup.clearCheck();
        ++questionIndex;
        if (questionIndex == 3) {
            //move to result fragment
            Log.i("TEST", "Move to next fragment...");
            Navigation.findNavController(view).navigate(R.id.resultFragment);
        } else {
            //render next question
            var q =  qlist[questionIndex]
            RenderQuestion(q)
        }
    }

    private fun RenderQuestion(Q: Question) {
        tvQuestion.setText(Q.question);

        val Choices = Q.questionChoices.split("|");
        rdBtn1.setText(Choices[0]);
        rdBtn2.setText(Choices[1]);
        rdBtn3.setText(Choices[2]);

        Log.i("TEST","${Q.QId} ${Q.question} ${Q.questionChoices} ")
    }

    private fun UpdateQuestionAnswer(qIndex:Int,userSelection:Int){
        GlobalScope.launch {
            var selectedQuestion = qlist[qIndex];
            selectedQuestion.answer = userSelection;
            db.questionDao().UpdateQuestion(selectedQuestion);
        }
    }

}