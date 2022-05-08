package com.miu.quizapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
    private var numOfQuestions:Int = 0;

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
            numOfQuestions = qlist.size;
            FetchQuestion(questionIndex);
        }
        return view;
    }

    private  suspend fun FetchQuestion(index:Int){
        var q =  qlist[index]
        renderQuestion(q)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener({ onNextClick(view) });
        btnSkip.setOnClickListener({ onSkipClick(view) });


        radioGroup.setOnCheckedChangeListener { buttonView, isChecked ->
            // Toast.makeText(requireContext(),isChecked.toString(),Toast.LENGTH_SHORT).show()
            when (isChecked) {
                R.id.radia_id1 -> selectedAnswer = 0
                R.id.radia_id2 -> selectedAnswer = 1
                R.id.radia_id3 -> selectedAnswer = 2
                else -> selectedAnswer = -1
            }
           updateQuestionAnswer(questionIndex,selectedAnswer);
        }
    }


    private fun onNextClick(view: View) {
        moveToNextQuestion(view);
    }

    private fun onSkipClick(view: View) {
        radioGroup.clearCheck();
        moveToNextQuestion(view);
    }

    private fun moveToNextQuestion(view: View) {
        ++questionIndex;
        //  questionIndex == numOfQuestions-1
        if (questionIndex == 15) {
            //move to result fragment
            Navigation.findNavController(view).navigate(R.id.resultFragment);
        } else {
            //render next question
            radioGroup.clearCheck();
            var q =  qlist[questionIndex]
            renderQuestion(q)
        }
    }

    private fun renderQuestion(Q: Question) {
        tvQuestion.setText(Q.question);

        val choices = Q.questionChoices.split("|");
        rdBtn1.setText(choices[0]);
        rdBtn2.setText(choices[1]);
        rdBtn3.setText(choices[2]);
    }

    private  fun updateQuestionAnswer(qIndex:Int,userSelection:Int){
        GlobalScope.launch {
            var selectedQuestion = qlist[qIndex];
            selectedQuestion.answer = userSelection;

            print(selectedQuestion)
            db.questionDao().UpdateQuestion(selectedQuestion);
            print(selectedQuestion)

        }
    }

    private fun print(q:Question){
        Log.i("TEST","Question ${q.question}: Your selection is: ${q.answer}, correct answer is :${q.correctAnswer}\n")
    }
}