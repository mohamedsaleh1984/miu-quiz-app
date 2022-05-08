package com.miu.quizapp

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_result.*

class CorrectionFragment : Fragment() {
    private lateinit var answers: String;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_correction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var r = arguments;
        var dd = r?.getString("answers");
        tvCorrection.setText(Html.fromHtml(dd));
    }
}