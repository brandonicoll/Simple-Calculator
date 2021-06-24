package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric = false //updated realtime to store if the last click was numeric
    var lastDot = false //decimal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view : View){
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view : View){
        if (lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun removeZero(result: String) : String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length-2) //starts at normal index but cuts off last two characters
        }
        return value
    }


    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

                  //passed a string, return a boolean
    fun isOperatorAdded(value : String) : Boolean {
        return if (value.startsWith("-")){
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    fun onEqual (view: View) {
        if (lastNumeric){
            var tvValue = tvInput.text.toString() //storing the calculator text into new var
            var prefix = "" //empty prefix

            try {

                if (tvValue.startsWith("-")){ //checks if there is a prefix
                    prefix = "-" //sets it
                    tvValue = tvValue.substring(1) //creating a substring starting at the index 1 till the end of the string

                }

                if(tvValue.contains("-")) {

                    val splitValue = tvValue.split("-") //acts like a array with the split values
                    var one = splitValue[0] //position 1
                    var two = splitValue[1] //position 2

                    if (!prefix.isEmpty()){ //adding the prefix of - back to the position 1 -- AFTER THE SPLIT
                        one = prefix + one
                    }

                    tvInput.text = removeZero((one.toDouble() - two.toDouble()).toString())
                }

                else if(tvValue.contains("+")) {

                    val splitValue = tvValue.split("+") //acts like a array with the split values
                    var one = splitValue[0] //position 1
                    var two = splitValue[1] //position 2

                    if (!prefix.isEmpty()){ //adding the prefix of - back to the position 1 -- AFTER THE SPLIT
                        one = prefix + one
                    }

                    tvInput.text = removeZero((one.toDouble() + two.toDouble()).toString())
                }

                else if(tvValue.contains("*")) {

                    val splitValue = tvValue.split("*") //acts like a array with the split values
                    var one = splitValue[0] //position 1
                    var two = splitValue[1] //position 2

                    if (!prefix.isEmpty()){ //adding the prefix of - back to the position 1 -- AFTER THE SPLIT
                        one = prefix + one
                    }

                    tvInput.text = removeZero((one.toDouble() * two.toDouble()).toString())
                }
                else if(tvValue.contains("/")) {

                    val splitValue = tvValue.split("/") //acts like a array with the split values
                    var one = splitValue[0] //position 1
                    var two = splitValue[1] //position 2

                    if (!prefix.isEmpty()){ //adding the prefix of - back to the position 1 -- AFTER THE SPLIT
                        one = prefix + one
                    }

                    tvInput.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }


            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}