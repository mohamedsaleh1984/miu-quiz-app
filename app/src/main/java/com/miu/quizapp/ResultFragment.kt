package com.miu.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.miu.quizapp.database.Question
import com.miu.quizapp.database.QuestionDatabase
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ResultFragment : Fragment() {
    lateinit var  db:QuestionDatabase;
    lateinit var qlist:List<Question>;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =inflater.inflate(R.layout.fragment_result, container, false)

        db = Room.databaseBuilder(requireContext(), QuestionDatabase::class.java,"question-db").build();
        GlobalScope.launch {
            qlist = db.questionDao().GetAllQuestions();
            GetResult(qlist);
        }
        return  view;
    }

    private suspend fun GetResult(QList:List<Question>){
        var correctAnswer:Int = 0;
        var wrongAnswer:Int = 0;
        var skippedAnswer:Int = 0;
        var correction:String = "";
        var qIndex = 1;
        for(q in QList){
            if(q.answer == q.correctAnswer){
                correctAnswer++;
            }
            if(q.answer == q.correctAnswer && q.answer != -1){
                wrongAnswer++;

                var chekList = q.questionChoices.split("|");
                correction+="Question ${qIndex}: Your selection is: ${chekList[q.answer]}, correct answer is :${chekList[q.correctAnswer]}\n"
            }
            if(q.answer == -1){
                skippedAnswer++;
            }

            qIndex++;
        }

        tvCorrectAnswers.text = "Total correct answers is: ${correctAnswer}"
        tvScore.text = "${correctAnswer}/3"
        tvSubmittedAnswers.text = "${correctAnswer+wrongAnswer}/3";
        tvWrongAnswers.text ="Total wrong answers is: ${wrongAnswer}"
        tvCorrection.text = correction
    }
}