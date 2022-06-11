package com.example.synuapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject

class ProfileAsesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_asesor)
        val boletaText = findViewById<TextView>(R.id.boletaAsesorProfile)
        val nameText = findViewById<TextView>(R.id.nameAsesorProfile)
        val apPatText = findViewById<TextView>(R.id.apPatAsesorProfile)
        val apMatText = findViewById<TextView>(R.id.apMatAsesorProfile)
        val telefonoText = findViewById<TextView>(R.id.telefonoAsesorProfile)
        val mailText = findViewById<TextView>(R.id.mailAsesorProfile)
        val aprendizajeText = findViewById<TextView>(R.id.aprendizajeAsesorProfile)
        val jso = JSONObject(intent.getStringExtra("res"))
        boletaText.text = jso.getString("boleta")
        nameText.text = jso.getString("name")
        apPatText.text = jso.getString("apPat")
        apMatText.text = jso.getString("apMat")
        telefonoText.text = jso.getString("telefono")
        mailText.text = jso.getString("mail")
        aprendizajeText.text = jso.getString("aprendizaje")
    }
}