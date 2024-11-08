package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var resultEditText: EditText
    private var currentInput = ""
    private var operator = ""
    private var previousInput = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultEditText = findViewById(R.id.editTextResult)

        // Number buttons
        val buttonOne: Button = findViewById(R.id.buttonOne)
        val buttonTwo: Button = findViewById(R.id.buttonTwo)
        val buttonThree: Button = findViewById(R.id.buttonThree)
        val buttonFour: Button = findViewById(R.id.buttonFour)
        val buttonFive: Button = findViewById(R.id.buttonFive)
        val buttonSix: Button = findViewById(R.id.buttonSix)
        val buttonSeven: Button = findViewById(R.id.buttonSeven)
        val buttonEight: Button = findViewById(R.id.buttonEight)
        val buttonNine: Button = findViewById(R.id.buttonNine)
        val buttonZero: Button = findViewById(R.id.buttonZero)

        // Operator buttons
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)

        // Action buttons
        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)

        // Set onClickListeners for number buttons
        val numberButtons = listOf(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonZero)
        numberButtons.forEach { button ->
            button.setOnClickListener {
                currentInput += button.text.toString()
                resultEditText.setText(currentInput)
            }
        }

        // Set onClickListeners for operator buttons
        buttonAdd.setOnClickListener { setOperator("+") }
        buttonSubtract.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("*") }
        buttonDivide.setOnClickListener { setOperator("/") }

        // Set onClickListeners for clear and equals buttons
        buttonClear.setOnClickListener {
            currentInput = ""
            previousInput = ""
            operator = ""
            resultEditText.setText("")
        }

        buttonEquals.setOnClickListener {
            if (previousInput.isNotEmpty()) {
                val result = calculateResult(previousInput, currentInput, operator)
                resultEditText.setText(result.toString())
                currentInput = result.toString()
                operator = ""
                previousInput = ""
            }
        }
    }

    private fun setOperator(op: String) {
        if (previousInput.isNotEmpty()) {
            currentInput = ""
        }
        operator = op
        previousInput = currentInput
        currentInput = ""
    }

    private fun calculateResult(num1: String, num2: String, operator: String): Double {
        val firstNumber = num1.toDouble()
        val secondNumber = num2.toDouble()
        return when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            else -> 0.0
        }
    }
}