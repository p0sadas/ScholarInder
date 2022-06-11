package com.example.synuapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text

class ProfileAsesorado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_asesorado)
        val boletaText = findViewById<TextView>(R.id.boletaAsesoradoProfile)
        val nameText = findViewById<TextView>(R.id.nameAsesoradoProfile)
        val apPatText = findViewById<TextView>(R.id.apPatAsesoradoProfile)
        val apMatText = findViewById<TextView>(R.id.apMatAsesoradoProfile)
        val telefonoText = findViewById<TextView>(R.id.telefonoAsesoradoProfile)
        val mailText = findViewById<TextView>(R.id.mailAsesoradoProfile)
        val aprendizajeText = findViewById<TextView>(R.id.aprendizajeAsesoradoProfile)
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