package com.example.synuapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class InicioAsesorado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_asesorado)
        val namUser = findViewById<TextView>(R.id.nameUserText)
        val jso = JSONObject(intent.getStringExtra("res"))
        namUser.text = jso.getString("name")
        val btnPerfil = findViewById<Button>(R.id.btnProfileAsesorado)
        val btnNuevoCursoAsesorado = findViewById<Button>(R.id.btnPedirCurso)
        val btnMisCursos = findViewById<Button>(R.id.btnMisCursos)
        val boletaAsesorado = jso.getString("boleta")
        var asesorias: String? = ""
        var uasPass:String? = ""
        var uasPass2:String? = ""
        var nameList=ArrayList<String>()
        val intent3 = Intent(this,MisAsesorias::class.java)
        val intent2 = Intent(this,NuevoCursoAsesorado::class.java)
        fun sig(){
            var asesoriasAsesorado=ArrayList<String>()
            nameList.clear()
            var curso = ""
            var s:Boolean = false
            var uas=ArrayList<String>()
            if (uasPass != null) {
                uasPass!!.forEach { c: Char ->
                    if (c.toString()=="{"){
                        s = true
                    }
                    if (s){
                        curso += c

                    }
                    if (c.toString()=="}"){
                        s = false
                        uas.add(curso)
                        curso = ""

                    }
                }
            }

            if (asesorias != null) {
                asesorias!!.forEach { c: Char ->
                    if (c.toString() == "{"){
                        s = true
                    }
                    if (s){
                        curso += c
                    }
                    if (c.toString() == "}"){
                        s = false
                        asesoriasAsesorado.add(curso)
                        curso = ""
                    }
                }
            }
            asesoriasAsesorado.forEach { s:String ->
                uas.forEach { m: String ->
                    var idUa = JSONObject(m).getString("idUa")
                    if(idUa == JSONObject(s).getString("idUa")){
                        nameList.add(JSONObject(m).getString("name").toString())
                    }
                }
            }
            intent3.putExtra("asesorias",nameList)
            startActivity(intent3)
        }
        fun sig2(){
            var asesoriasAsesorado=ArrayList<String>()
            nameList.clear()
            var curso = ""
            var s:Boolean = false
            var uas=ArrayList<String>()
            var uas2=ArrayList<String>()
            if (uasPass != null) {
                uasPass!!.forEach { c: Char ->
                    if (c.toString()=="{"){
                        s = true
                    }
                    if (s){
                        curso += c

                    }
                    if (c.toString()=="}"){
                        s = false
                        uas.add(curso)
                        curso = ""

                    }
                }
            }

            if (asesorias != null) {
                asesorias!!.forEach { c: Char ->
                    if (c.toString() == "{"){
                        s = true
                    }
                    if (s){
                        curso += c
                    }
                    if (c.toString() == "}"){
                        s = false
                        asesoriasAsesorado.add(curso)
                        curso = ""
                    }
                }
            }

            if (uasPass2 != null) {
                uasPass2!!.forEach { c: Char ->
                    if (c.toString() == "{"){
                        s = true
                    }
                    if (s){
                        curso += c
                    }
                    if (c.toString() == "}"){
                        s = false
                        uas2.add(curso)
                        curso = ""
                    }
                }
            }

            intent2.putExtra("res",jso.toString())
            Log.i("forp",uas.toString())
            Log.i("forp2",asesoriasAsesorado.toString())
            Log.i("forp3",uas2.toString())
            intent2.putExtra("asesores",uas)
            intent2.putExtra("asesorias",asesoriasAsesorado)
            intent2.putExtra("uas",uas2)
            startActivity(intent2)
        }
        btnPerfil.setOnClickListener{
            val intent = Intent(this,ProfileAsesorado::class.java)
            intent.putExtra("res",jso.toString())
            startActivity(intent)
        }
        btnNuevoCursoAsesorado.setOnClickListener{

            val queue2 = Volley.newRequestQueue(this)
            val url = "https://synuapp2.000webhostapp.com/asesoriasGeneral.php"
            var jar: StringRequest =  object : StringRequest(Request.Method.POST,url,
                Response.Listener<String>{ Response ->
                    asesorias = Response.toString()
                }, Response.ErrorListener { error->
                    Log.i("Response Fail: ", "["+error+"]" )
                    Toast.makeText(this, "["+error+"]", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): Map<String, String> {
                    val jso: MutableMap<String, String> = HashMap()
                    jso["boleta"] = "$boletaAsesorado"
                    return jso
                }
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Content-Type"] = "application/x-www-form-urlencoded"
                    return params
                }
            }
            queue2.add(jar)
            val url2 = "https://synuapp2.000webhostapp.com/asesoresGet.php"
            var jar2: StringRequest =  object : StringRequest(Request.Method.POST,url2,
                Response.Listener<String>{ Response ->
                    uasPass = Response.toString()
                }, Response.ErrorListener { error->
                    Log.i("Response Fail: ", "["+error+"]" )
                    Toast.makeText(this, "["+error+"]", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): Map<String, String> {
                    val jso: MutableMap<String, String> = HashMap()

                    return jso
                }
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Content-Type"] = "application/x-www-form-urlencoded"
                    return params
                }
            }
            queue2.add(jar2)

            val url3 = "https://synuapp2.000webhostapp.com/asesoriasBase.php"
            var jar3: StringRequest =  object : StringRequest(Request.Method.POST,url3,
                Response.Listener<String>{ Response ->
                    uasPass2 = Response.toString()
                    sig2()
                }, Response.ErrorListener { error->
                    Log.i("Response Fail: ", "["+error+"]" )
                    Toast.makeText(this, "["+error+"]", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): Map<String, String> {
                    val jso: MutableMap<String, String> = HashMap()

                    return jso
                }
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Content-Type"] = "application/x-www-form-urlencoded"
                    return params
                }
            }
            queue2.add(jar3)
        }

        btnMisCursos.setOnClickListener{

            val queue = Volley.newRequestQueue(this)

            val url = "https://synuapp2.000webhostapp.com/asesoriasAsesorado.php"
            var jar: StringRequest =  object : StringRequest(Request.Method.POST,url,
                Response.Listener<String>{ Response ->
                    asesorias = Response.toString()
                }, Response.ErrorListener { error->
                    Log.i("Response Fail: ", "["+error+"]" )
                    Toast.makeText(this, "["+error+"]", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): Map<String, String> {
                    val jso: MutableMap<String, String> = HashMap()
                    jso["boleta"] = "$boletaAsesorado"
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
            val url2 = "https://synuapp2.000webhostapp.com/asesoriasBase.php"
            var jar2: StringRequest =  object : StringRequest(Request.Method.POST,url2,
                Response.Listener<String>{ Response ->
                    uasPass = Response.toString()
                    sig()
                }, Response.ErrorListener { error->
                    Log.i("Response Fail: ", "["+error+"]" )
                    Toast.makeText(this, "["+error+"]", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): Map<String, String> {
                    val jso: MutableMap<String, String> = HashMap()

                    return jso
                }
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Content-Type"] = "application/x-www-form-urlencoded"
                    return params
                }
            }
            queue.add(jar2)
        }
    }
}