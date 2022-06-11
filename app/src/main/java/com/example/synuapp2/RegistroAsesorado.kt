package com.example.synuapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegistroAsesorado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_asesorado)
        val button: Button = findViewById(R.id.btnContinuarRegistroAsesorado)
        var name = findViewById(R.id.nameRegisAsesorado) as EditText
        var apPat = findViewById(R.id.apTextAsesorado) as EditText
        var apMat = findViewById(R.id.amTextAsesorado) as EditText
        var boleta = findViewById(R.id.boletaTextAsesorado) as EditText
        var telefono = findViewById(R.id.celTextAsesorado) as EditText
        var mail = findViewById(R.id.correoTextAsesorado) as EditText
        var pass = findViewById<EditText>(R.id.passTextAsesorado)
        button.setOnClickListener{
            val intent = Intent(this,TestAsesorado::class.java)
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