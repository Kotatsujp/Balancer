package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class bankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank)
    }
    fun onClick(view: View) {
        try {
            val ope = intent.getStringExtra("ope")
            var input : TextView = findViewById(R.id.inputDrower)
            var money = if(ope=="0")input.text.toString().toInt()
            else -input.text.toString().toInt()
            val resultIntent = Intent()
            resultIntent.putExtra("pocket", money.toString() )
            setResult(Activity.RESULT_OK, resultIntent)

            finish()

        }catch(e: Exception){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            finish()
        }
    }
}