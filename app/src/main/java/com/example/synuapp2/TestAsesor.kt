package com.example.synuapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class TestAsesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_asesor)
        val button = findViewById<Button>(R.id.btnRegistroAsesor)
        val namePass= intent.getStringExtra("name")
        val apPat = intent.getStringExtra("apPat")
        val apMat = intent.getStringExtra("apMat")
        val boleta = intent.getStringExtra("boleta")
        val telefono = intent.getStringExtra("telefono")
        val mail = intent.getStringExtra("mail")
        val pass = intent.getStringExtra("pass")
        val rdAs = findViewById<RadioGroup>(R.id.rgAsesor)
        button.setOnClickListener{
            val so1: Int = rdAs!!.checkedRadioButtonId
            val rd1 = findViewById<RadioButton>(so1)
            val queue = Volley.newRequestQueue(this)
            val url = "https://synuapp2.000webhostapp.com/registerAsesor.php"
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
                    jso["boleta"] = "$boleta"
                    jso["name"]="$namePass"
                    jso["apPat"]="$apPat"
                    jso["apMat"]="$apMat"
                    jso["telefono"]="$telefono"
                    jso["mail"] = "$mail"
                    jso["aprendizaje"] =rd1.text.toString()
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
        }
    }
}