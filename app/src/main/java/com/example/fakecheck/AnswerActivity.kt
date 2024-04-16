package com.example.fakecheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AnswerActivity : AppCompatActivity() {
    lateinit private var answerTextView: TextView
    lateinit private var button: Button
    lateinit private var answer: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_answer)
        answerTextView = findViewById(R.id.textView)
        answer = intent.getStringExtra("answer") ?: "NOT FOUND"
        val array = answer.trim().split(" ")
        val ans1 = mutableListOf<String>()
        for (i in array) {
            if (i=="Достоверно" || i=="Недостоверно" || i=="информации") {
                ans1.add(i)
                break
            }
            else if (i=="Недостаточно") {
                ans1.add(i)
            }
        }
        for (i in ans1) {
            Log.d("salam", i)
        }
        Log.d("salam", "\n\n")
        Log.d("salam", answer)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this@AnswerActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}