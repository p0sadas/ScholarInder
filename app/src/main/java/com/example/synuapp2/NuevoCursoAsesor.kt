package com.example.synuapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class NuevoCursoAsesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_curso_asesor)
        val btnCurso = findViewById<Button>(R.id.btnRegistrarNuevoCurso)
        val materiaText = findViewById<EditText>(R.id.materiaNuevoCurso)
        val lugartext = findViewById<EditText>(R.id.lugarNuevoCurso)
        val time1 = findViewById<TimePicker>(R.id.time1)
        val time2 = findViewById<TimePicker>(R.id.time2)
        var h1=""
        var h2 = ""
        val asesor = JSONObject(intent.getStringExtra("res"))
        time1.setOnTimeChangedListener(TimePicker.OnTimeChangedListener { timePicker, hour, minute ->
            h1 = hour.toString() + ":" + minute.toString()

        })
        time2.setOnTimeChangedListener(TimePicker.OnTimeChangedListener { timePicker, hour, minute ->
            h2 = hour.toString() + ":" + minute.toString()

        })

        btnCurso.setOnClickListener{
            val queue = Volley.newRequestQueue(this)
            val url = "https://synuapp2.000webhostapp.com/registerAsesoriaAsesor.php"
            var jar: StringRequest =  object : StringRequest(Request.Method.POST,url,
                Response.Listener<String>{ Response ->
                    Log.i("Response Success: ",Response.toString())
                    Toast.makeText(this,Response.toString(), Toast.LENGTH_LONG).show()
                }, Response.ErrorListener { error->
                    Log.i("Response Fail: ", "["+error+"]")
                            Toast.makeText(this, "["+error+"]", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams():Map<String,String>{
                    val jso: MutableMap<String,String> = HashMap()
                    jso["idUa"]="${materiaText.text}"
                    jso["boletaAsesor"]="${asesor.getString("boleta")}"
                    jso["lugar"]="${lugartext.text.toString()}"
                    jso["horaI"]="$h1"
                    jso["horaF"]="$h2"
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