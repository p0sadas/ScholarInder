package com.example.synuapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject

class InicioAsesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_asesor)
        val namUser = findViewById<TextView>(R.id.nameUserAsesorText)
        val jso = JSONObject(intent.getStringExtra("res"))
        namUser.text = jso.getString("name")
        val btnPerfil = findViewById<Button>(R.id.btnProfileAsesor)
        val btnNuevo = findViewById<Button>(R.id.btnRegistrarCursoAsesor)
        btnPerfil.setOnClickListener{
            val intent = Intent(this,ProfileAsesor::class.java)
            intent.putExtra("res",jso.toString())
            startActivity(intent)
        }
        btnNuevo.setOnClickListener{
            val intent2 = Intent(this,NuevoCursoAsesor::class.java)
            intent2.putExtra("res",jso.toString())
            startActivity(intent2)
        }
    }
}