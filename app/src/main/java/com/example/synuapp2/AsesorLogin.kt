package com.example.synuapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class AsesorLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asesor_login)
        val butoon: Button = findViewById(R.id.btnLoginAsesor)
        val button2: Button = findViewById(R.id.btnRegistroAsesor)
        val boleta=findViewById<EditText>(R.id.asesorBoletaText)
        val pass = findViewById<EditText>(R.id.asesorPassText)
        button2.setOnClickListener{
            val intent = Intent(this,RegistroAsesor::class.java)
            startActivity(intent)
        }
        fun login(){
            val boleta = boleta.text.toString()
            val pass = pass.text.toString()
            if(!boleta.equals("") && !pass.equals("")){
                val queue = Volley.newRequestQueue(this)
                val url = "https://synuapp2.000webhostapp.com/loginAsesor.php"
                var jar: StringRequest =  object : StringRequest(Request.Method.POST,url,
                    Response.Listener<String>{ Response ->
                        Log.i("res: i",Response.toString())
                        val nameRes = Response.toString().substring(Response.toString().indexOf(":")+1,Response.toString().length)
                        if (Response.length>0){
                            val intent = Intent(this,InicioAsesor::class.java)
                            intent.putExtra("res",Response.toString())
                            startActivity(intent)

                        } else if(Response.toString().substring(0,Response.toString().indexOf(":")).equals("Nel")){
                            Toast.makeText(this, "Creedenciales Incorrectas", Toast.LENGTH_SHORT).show()
                        }
                    }, Response.ErrorListener { error->
                        Log.i("Response Fail: ", "["+error+"]" )
                        Toast.makeText(this, "["+error+"]", Toast.LENGTH_LONG).show()
                    }) {
                    override fun getParams(): Map<String, String> {
                        val jso: MutableMap<String, String> = HashMap()
                        jso["boleta"] = "$boleta"
                        jso["pass"] = "$pass"
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
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        butoon.setOnClickListener {
            login()
        }


    }
}