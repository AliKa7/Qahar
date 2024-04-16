package com.example.fakecheck

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
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
        val eTPrompt= findViewById<EditText>(R.id.eTPrompt)
        val btnSubmit= findViewById<Button>(R.id.btnSubmit)
        val tVResult= findViewById<TextView>(R.id.tVResult)

        btnSubmit.setOnClickListener {
            val prompt = "Проверь информацию на достоверность/недостоверность, разбив ответ на три параграфа - тезис, аргументы и источники. Информация для проверки: " + eTPrompt.text.toString()

            // Change the button text to "aa"
            btnSubmit.text = "aa"
            // Launch coroutine for API call
            GlobalScope.launch(Dispatchers.IO) {
                val generativeModel = GenerativeModel(
                    // For text-only input, use the gemini-pro model
                    modelName = "gemini-pro",
                    apiKey = "AIzaSyB44Xq9X5Imq0LmZZdcwdajTOPLLIB30ew"
                )

                // Make the API call
                val response = generativeModel.generateContent(prompt)

                // Update UI with response
                tVResult.text = response.text ?: "something went wrong"

                // Revert button text back to "submit"
                btnSubmit.text = "submit"
            }

        }
    }
}