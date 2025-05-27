package com.example.final_android

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var inputNumber:EditText
       private lateinit var checkButton:Button
    private lateinit var resetButton:Button
    private lateinit var resultText:TextView
     private lateinit var attemptsText:TextView

    private var randomNumber = 0
    private var attemptsLeft = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.inputNumber)
        checkButton = findViewById(R.id.checkButton)
        resetButton = findViewById(R.id.resetButton)
        resultText = findViewById(R.id.resultText)
        attemptsText = findViewById(R.id.attemptsText)

        startNewGame()
        checkButton.setOnClickListener {
            val guessText = inputNumber.text.toString()

            if (guessText.isEmpty()) {
                Toast.makeText(this,"يرجى ادخال رقم", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val guess = guessText.toInt()

            if (guess == randomNumber) {
                resultText.text = "مبروك! خمنت الرقم الصحيح "
                checkButton.isEnabled = false
            } else if (guess > randomNumber) {
                resultText.text = "الرقم المدخل اكبر من الرقم الصحيح"
            } else {
                resultText.text = "الرقم المدخل اصغر من الرقم الصحيح"
            }
            attemptsText.text = "المحاولات المتبقية: $attemptsLeft"

                if (attemptsLeft == 0 && guess != randomNumber) {
                resultText.text = "انتهت المحاولات! الرقم الصحيح هو $randomNumber"
                checkButton.isEnabled = false
            }
        }
        resetButton.setOnClickListener {

            startNewGame()

            resultText.text =""
                attemptsText.text = "المحاولات المتبقية: $attemptsLeft"
                inputNumber.text.clear()
              checkButton.isEnabled=true
        }
    }
    private fun startNewGame(){
        randomNumber =Random.nextInt(1, 21)

        attemptsLeft = 5
    }
}
