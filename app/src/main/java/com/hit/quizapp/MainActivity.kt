package com.hit.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val question: List<Question> =
        listOf(
            Question(R.string.pertanyaan_rendang, true, false),
            Question(R.string.pertanyaan_kemerdekaan, true, false),
            Question(R.string.pertanyaan_pancasila, false, false),
            Question(R.string.pertanyaan_presiden, false, false),
            Question(R.string.pertanyaan_wakil_presiden, true, false)
        )
    var questionIndex: Int = 0
    var skor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_question.text = getText(question[0].question)
        next_button.isEnabled = false

        true_button.setOnClickListener(this)
        false_button.setOnClickListener(this)
        next_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            true_button -> {
                when (question[questionIndex].quevalue) {
                    true -> {
                        addSkor()
                        Toast.makeText(this, "Betul sekali!", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        Toast.makeText(this, "Kamu salah... :(", Toast.LENGTH_SHORT).show()
                    }
                }
                next_button.isEnabled = true
                buttonPressDisable()
                checkQuestionEnd()
            }
            false_button -> {
                when (question[questionIndex].quevalue) {
                    true -> {
                        Toast.makeText(this, "Kamu salah... :(", Toast.LENGTH_SHORT).show()
                    }
                    false -> {
                        addSkor()
                        Toast.makeText(this, "Betul sekali!", Toast.LENGTH_SHORT).show()
                    }
                }
                next_button.isEnabled = true
                buttonPressDisable()
                checkQuestionEnd()
            }
            next_button -> {
                nextQuestion()
                buttonPressEnable()
            }
        }
    }

    private fun buttonPressDisable() {
        true_button.isEnabled = false
        false_button.isEnabled = false
    }

    private fun buttonPressEnable() {
        true_button.isEnabled = true
        false_button.isEnabled = true
    }

    private fun nextQuestion() {
        questionIndex++
        Log.d("index", "count: $questionIndex")
        txt_question.text = getText(question[questionIndex].question)
    }

    private fun addSkor() {
        skor += 2
        txt_point.text = skor.toString()
    }

    private fun checkQuestionEnd() {
        if (questionIndex == 4) {
            next_button.isEnabled = false
            checkSkor()
        }
    }

    private fun checkSkor() {
        val nilaiSkor : Int = txt_point.text.toString().toInt()
        if (nilaiSkor >= 8) {
            txt_question.text = "Selamat kamu mendapatkan nilai memuaskan :)"
        } else if (nilaiSkor < 8) {
            txt_question.text = "Sayang sekali kamu mendapatkan nilai kurang memuaskan :("
        } else {
            txt_question.text = "Error X_X"
        }
    }

}