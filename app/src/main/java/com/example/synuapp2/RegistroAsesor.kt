package com.example.synuapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegistroAsesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resgitro_asesor)
        val button: Button = findViewById(R.id.btnContinuarRegistroAsesor)
        var name = findViewById(R.id.nameRegisAsesor) as EditText
        var apPat = findViewById(R.id.apTextAsesor) as EditText
        var apMat = findViewById(R.id.amTextAsesor) as EditText
        var boleta = findViewById(R.id.boletaTextAsesor) as EditText
        var telefono = findViewById(R.id.celTextAsesor) as EditText
        var mail = findViewById(R.id.correoTextAsesor) as EditText
        var pass = findViewById<EditText>(R.id.passTextAsesor)
        button.setOnClickListener{
            val intent = Intent(this,TestAsesor::class.java)
            intent.putExtra("name",name.text.toString())
            intent.putExtra("apPat",apPat.text.toString())
            intent.putExtra("apMat",apMat.text.toString())
            intent.putExtra("boleta",boleta.text.toString())
            intent.putExtra("telefono",telefono.text.toString())
            intent.putExtra("mail",mail.text.toString())
            intent.putExtra("pass",pass.text.toString())
            startActivity(intent)
        }
    }
}