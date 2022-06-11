package com.example.synuapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import org.w3c.dom.Text

class RevirsarFinalAsesorado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revirsar_final_asesorado)
        var btnInscribirme = findViewById<Button>(R.id.btnResumenAsesorado)
        var txtCurso=findViewById<TextView>(R.id.txtCursoResumen)
        var txtAsesor = findViewById<TextView>(R.id.txtAsesorResumen)
        var txtHorario = findViewById<TextView>(R.id.txtHorarioResumen)
        var curso = intent.getStringExtra("curso")
        var asesor = intent.getStringExtra("asesor")
        var asesoradoBoleta = JSONObject(intent.getStringExtra("asesorado")).getString("boleta")
        var res = intent.getStringExtra("asesorado")
        txtCurso.text = curso
        txtAsesor.text = asesor

        var idCurso = intent.getIntExtra("idCurso",0)
        var idAsesor = intent.getStringExtra("idAsesor")
        txtHorario.text = "13:00 - 15:00"

        btnInscribirme.setOnClickListener{

            val queue = Volley.newRequestQueue(this)
            val url = "https://synuapp2.000webhostapp.com/registerAsesoriaAsesorado.php"
            var jar: StringRequest =  object : StringRequest(Request.Method.POST,url,
                Response.Listener<String>{ Response ->
                    Log.i("Response Success: ",Response.toString())
                    Toast.makeText(this,Response.toString(), Toast.LENGTH_LONG).show()

                }, Response.ErrorListener { error->
                    Log.i("Response Fail: ", "["+error+"]" )
                    Toast.makeText(this, "["+error+"]", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams():Map<String,String>{
                    val jso: MutableMap<String,String> = HashMap()
                    jso["idUa"] = "$idCurso"
                    jso["boleta"]="$asesoradoBoleta"
                    jso["boletaAsesor"]="$idAsesor"
                    return jso
                }
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Content-Type"] = "application/x-www-form-urlencoded"
                    return params
                }

            }
            queue.add(jar)
        }
    }
}