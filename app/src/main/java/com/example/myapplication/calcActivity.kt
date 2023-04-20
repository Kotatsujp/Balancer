package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception


class calcActivity : AppCompatActivity() {
    private val spinnerItems = arrayOf("現金", "銀行")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        val spinner = findViewById<Spinner>(R.id.asset)
        val adapter = ArrayAdapter(this, R.layout.spinner_big_item, spinnerItems)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        adapter.setDropDownViewResource(R.layout.sp_dd)

    }

    fun onFinish(view: View){
        try {
            val ope = intent.getStringExtra("ope")
            val assTextView = findViewById<Spinner>(R.id.asset)
            val difTextView = findViewById<EditText>(R.id.howmuch)
            val type = assTextView.selectedItemPosition
            val difString = difTextView.text.toString()

            val dif = if (ope == "income") {
                difString
            } else {
                (-difString.toInt()).toString()
            }
            val resultIntent = Intent()
            resultIntent.putExtra("dif", dif)
            resultIntent.putExtra("type", type.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }catch(e:Exception){
            Toast.makeText(this, "数字を入力してください", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}