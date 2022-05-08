package com.miu.quizapp

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.room.Room
import com.miu.quizapp.database.Question
import com.miu.quizapp.database.QuestionDatabase
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ResultFragment : Fragment() {
    lateinit var  db:QuestionDatabase;
    lateinit var qlist:List<Question>;
    var correctAnswer:Int = 0;
    var wrongAnswer:Int = 0;
    var skippedAnswer:Int = 0;
    var correction:String = "<b>Answers Correction:</b><br/>";

    var count:Int = 0;

    private lateinit var btnExit: Button
    private lateinit var btnView: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =inflater.inflate(R.layout.fragment_result, container, false)

        btnExit = view.findViewById(R.id.btnExit);
        btnView = view.findViewById(R.id.btnView);


        db = Room.databaseBuilder(requireContext(), QuestionDatabase::class.java,"question-db").build();
        GlobalScope.launch {
            count = db.questionDao().RowsCount();
            qlist = db.questionDao().GetAllQuestions();

            var qIndex = 0;
            for(q in qlist){
                if(q.answer == q.correctAnswer){
                    correctAnswer++;
                }
                else if(q.answer != q.correctAnswer && q.answer != -1){
                    wrongAnswer++;

                    var chekList = q.questionChoices.split("|");
                   // correction+="<b>Question ${qIndex+1} : <br/>Your selection is : ${chekList[q.answer]}<br/>correct answer is : ${chekList[q.correctAnswer]}</b><br/><br/>"
                    correction+="<b>Question ${qIndex+1}:<br/>Your selection is: ${chekList[q.answer]} <br/>correct answer is :${chekList[q.correctAnswer]}<br/><b/>"
                }
                else if(q.answer == -1){
                    skippedAnswer++;
                }
                qIndex++;
            }

            tvCorrectAnswers.setText(Html.fromHtml("<b>Total correct answers is : ${correctAnswer}</b>"))
            tvScore.setText(Html.fromHtml("<b>Your score is : ${correctAnswer}/${count}</b>"));
            tvSubmittedAnswers.setText(Html.fromHtml("<b>Total submitted answers is : ${correctAnswer+wrongAnswer}/${count}</b>"))
            tvWrongAnswers.setText(Html.fromHtml("<b>Total wrong answers is : ${wrongAnswer}</b>"))
        }
        return  view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnExit.setOnClickListener({onExitClick(view)})
        btnView.setOnClickListener({onViewClick(view)})
    }

    private fun onExitClick(view:View){
        activity?.finish()
    }

    private fun onViewClick(view:View)
    {
        val bundle = bundleOf("answers" to correction)
        Navigation.findNavController(view).navigate(R.id.correctionFragment,bundle);
    }
}