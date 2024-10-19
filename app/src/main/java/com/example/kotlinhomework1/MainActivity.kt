package com.example.kotlinhomework1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edName = findViewById<EditText>(R.id.edName)
        val tvText = findViewById<TextView>(R.id.tvText)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnMora = findViewById<Button>(R.id.btnMora)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvWinner = findViewById<TextView>(R.id.tvWinner)
        val tvMyMora = findViewById<TextView>(R.id.tvMyMora)
        val tvTargetMora = findViewById<TextView>(R.id.tvTargetMora)

        val mora = mapOf(0 to "剪刀", 1 to "石頭", 2 to "布")

        btnMora.setOnClickListener {
            if (edName.text.isEmpty()) {
                tvText.text = "請輸入玩家姓名"
                return@setOnClickListener
            }

            // 我方出拳
            val myMora = when (radioGroup.checkedRadioButtonId) {
                R.id.btnScissor -> 0
                R.id.btnStone -> 1
                else -> 2
            }

            // 電腦出拳
            val targetMora = (0..2).random()

            // 將資訊存入結果
            tvName.text = "名字\n${edName.text}"
            tvMyMora.text = "我方出拳\n${mora[myMora]}"
            tvTargetMora.text = "電腦出拳\n${mora[targetMora]}"

            // 判斷勝負
            if (myMora == 0 && targetMora == 2 || myMora == 1 && targetMora == 0 || myMora == 2 && targetMora == 1) {
                tvText.text = "恭喜您獲勝了！！！"
                tvWinner.text = "勝利者\n${edName.text}"
            } else if (myMora == targetMora) {
                tvText.text = "平局，請再試一次！"
                tvWinner.text = "勝利者\n平手"
            } else {
                tvText.text = "可惜，電腦獲勝了！"
                tvWinner.text = "勝利者\n電腦"
            }
        }
    }
}