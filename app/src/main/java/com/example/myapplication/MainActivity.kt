package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import org.w3c.dom.Text


class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var pocketMoney: TextView = findViewById(R.id.money)
        var bankMoney: TextView = findViewById(R.id.bank)
        pocketMoney.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)
        bankMoney.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)

        findViewById<Button>(R.id.income).setOnClickListener(this)
        findViewById<Button>(R.id.spend).setOnClickListener(this)
        findViewById<Button>(R.id.deposit).setOnClickListener(this)
        findViewById<Button>(R.id.drawer).setOnClickListener(this)
    }

    private val incomeActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val data: Intent? = result.data
                val dif = Integer.parseInt(data?.getStringExtra("dif"))

                val type = data?.getStringExtra("type")

                //メイン画面に入力した文字を表示するなどの処理を行う
                val balance: TextView = if (type == "0") {
                    findViewById(R.id.money)
                } else {
                    findViewById(R.id.bank)
                }
                var now = balance.text.toString().replace("¥".toRegex(), "")

                val ans = now.toInt() + dif
                balance.text = ("¥" + ans.toString())
            }catch(e: Exception){
                    Toast.makeText(this, "数字を入力してください", Toast.LENGTH_LONG).show()
                }
        }else{
            //none
        }
    }
    private val bankActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val data: Intent? = result.data
                val pocket = Integer.parseInt(data?.getStringExtra("pocket"))

                //メイン画面に入力した文字を表示するなどの処理を行う
                val balance: TextView = findViewById(R.id.money)
                val bankBalance : TextView = findViewById(R.id.bank)

                balance.text ="¥" +  (balance.text.toString().replace("¥".toRegex(), "").toInt() + pocket).toString()
                bankBalance.text = (bankBalance.text.toString().replace("¥".toRegex(), "").toInt() - pocket).toString()
            }catch(e: Exception){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }else{
            //none
        }
    }

    override fun onClick(view: View){
        when(view.id){
            R.id.income ->{
                val intent = Intent(this, calcActivity::class.java)
                intent.putExtra("ope", "income")
                incomeActivityResultLauncher.launch(intent)
            }
            R.id.spend ->{
                val intent = Intent(this, calcActivity::class.java)
                intent.putExtra("ope", "spend")
                incomeActivityResultLauncher.launch(intent)
            }
            R.id.drawer ->{
                val intent = Intent(this, bankActivity:: class.java)
                intent.putExtra("ope", "0")
                bankActivityResultLauncher.launch(intent)
            }
            R.id.deposit ->{
                val intent = Intent(this, bankActivity:: class.java)
                intent.putExtra("ope", "1")
                bankActivityResultLauncher.launch(intent)
            }

        }
    }
}

