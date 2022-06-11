package com.example.synuapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btnAsesorado)
        val button2:Button = findViewById(R.id.btnAsesorMain)
        button.setOnClickListener{
            val intent = Intent(this,AsesoradoLogin::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener{
            val intent2 = Intent(this,AsesorLogin::class.java)
            startActivity(intent2)
        }
    }


}