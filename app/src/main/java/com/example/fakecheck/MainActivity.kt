package com.example.fakecheck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var eTPrompt1: EditText
    private lateinit var eTPrompt2: EditText
    private lateinit var eTPrompt3: EditText
    private lateinit var eTPrompt4: EditText
    private lateinit var tVResult: TextView
    private lateinit var btnMore: Button
    private var answer = ""

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        eTPrompt1 = findViewById(R.id.eTPrompt1)
        eTPrompt2 = findViewById(R.id.eTPrompt2)
        eTPrompt3 = findViewById(R.id.eTPrompt3)
        eTPrompt4 = findViewById(R.id.eTPrompt4)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        tVResult = findViewById(R.id.tVResult)
        tVResult.visibility = View.INVISIBLE
        btnMore = findViewById(R.id.btnMore)
        btnMore.visibility = View.INVISIBLE
        btnSubmit.setOnClickListener {
            runBlocking {
                tVResult.visibility = View.VISIBLE
            }
            var prompt = "Проверь информацию на достоверность/недостоверность, разбив ответ на три параграфа: вердикт из одного слова - Достоверно/Недостоверно/Недостаточно информации, аргументы, источники. Запрос: "
            prompt += ("\n Дата: " + eTPrompt1.text)
            prompt += ("\n Страна: " + eTPrompt2.text)
            prompt += ("\n Регион/Город: " + eTPrompt3.text)
            prompt += ("\n Произошедшее: " + eTPrompt4.text)
            runBlocking {
                val generativeModel = GenerativeModel(
                    modelName = "gemini-pro",
                    apiKey = "AIzaSyB44Xq9X5Imq0LmZZdcwdajTOPLLIB30ew"
                )
                val response = generativeModel.generateContent(prompt)
                answer = response.text ?: "something went wrong"
                btnMore.visibility = View.VISIBLE
            }
        }

        btnMore.setOnClickListener {
            val intent = Intent(this@MainActivity, AnswerActivity::class.java)
            intent.putExtra("answer", answer)
            startActivity(intent)
        }
    }
}