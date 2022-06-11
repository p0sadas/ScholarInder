package com.example.synuapp2

import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import java.util.*
import kotlin.collections.HashMap


class TestAsesorado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_asesorado)
        val button: Button = findViewById(R.id.btnContinuar2)

        val namePass= intent.getStringExtra("name")
        val apPat = intent.getStringExtra("apPat")
        val apMat = intent.getStringExtra("apMat")
        val boleta = intent.getStringExtra("boleta")
        val telefono = intent.getStringExtra("telefono")
        val mail = intent.getStringExtra("mail")
        val pass = intent.getStringExtra("pass")

        button.setOnClickListener{
            val idg1 = findViewById<RadioGroup>(R.id.rd1)
            val idg2 = findViewById<RadioGroup>(R.id.rd2)
            val idg3 = findViewById<RadioGroup>(R.id.rd3)
            val idg4 = findViewById<RadioGroup>(R.id.rd4)
            val idg5 = findViewById<RadioGroup>(R.id.rd5)
            val idg6 = findViewById<RadioGroup>(R.id.rd6)
            var conVisual =0
            var conAuditivo=0
            var conKinestesico=0
            var tApren = ""
            if(idg1.id!= -1){
                val so1: Int = idg1!!.checkedRadioButtonId
                val rd1 = findViewById<RadioButton>(so1)
                if(rd1.text.toString()=="Examen Oral") {
                    ++conVisual

                } else if(rd1.text.toString()=="Examen oral"){
                    ++conAuditivo
                }else if(rd1.text.toString()=="Examen de opción múltiple"){
                    ++conKinestesico
                }

            }
            if(idg2.id!= -1){
                val so1: Int = idg2!!.checkedRadioButtonId
                val rd1 = findViewById<RadioButton>(so1)
                if(rd1.text.toString()=="Memorizo lo que veo y recuerdo la imagen (por ejemplo, la página del libro)") {
                    ++conVisual

                } else if(rd1.text.toString()=="Memorizo mejor si repito lo estudiado rítmicamente y recuerdo paso a paso"){
                    ++conAuditivo
                }else if(rd1.text.toString()=="Memorizo a base de pasear y mirar, y recuerdo una idea general mejor que los detalles"){
                    ++conKinestesico
                }

            }
            if(idg3.id!= -1){
                val so1: Int = idg3!!.checkedRadioButtonId
                val rd1 = findViewById<RadioButton>(so1)
                if(rd1.text.toString()=="Escuchando al profesor.") {
                    ++conVisual
                } else if(rd1.text.toString()=="Me aburro y espero a que me den algo para hacer."){
                    ++conAuditivo
                }else if(rd1.text.toString()=="Leyendo el libro o la pizarra."){
                    ++conKinestesico
                }

            }
            if(idg4.id!= -1){
                val so1: Int = idg4!!.checkedRadioButtonId
                val rd1 = findViewById<RadioButton>(so1)
                if(rd1.text.toString()=="Prefiero escuchar chistes que leer cómics.") {
                    ++conVisual
                } else if(rd1.text.toString()=="Mis cuadernos y libretas están ordenados y bien presentados, me molestan los tachones y las correcciones."){
                    ++conAuditivo
                }else if(rd1.text.toString()=="Me gusta tocar las cosas y tiendo a acercarme mucho a la gente cuando hablo con alguien."){
                    ++conKinestesico
                }

            }
            if(idg5.id!= -1){
                val so1: Int = idg5!!.checkedRadioButtonId
                val rd1 = findViewById<RadioButton>(so1)
                if(rd1.text.toString()=="Ver películas.") {
                    ++conVisual
                } else if(rd1.text.toString()=="Escuchar música."){
                    ++conAuditivo
                }else if(rd1.text.toString()=="Bailar."){
                    ++conKinestesico
                }

            }
            if(idg6.id!= -1){
                val so1: Int = idg6!!.checkedRadioButtonId
                val rd1 = findViewById<RadioButton>(so1)
                if(rd1.text.toString()=="Repitiendo en voz alta.") {
                    ++conVisual
                } else if(rd1.text.toString()=="Escribiéndolo varias veces."){
                    ++conAuditivo
                }else if(rd1.text.toString()=="Relacionándolo con algo, a poder ser divertido."){
                    ++conKinestesico
                }

            }

            if(conVisual>conAuditivo && conVisual>conKinestesico){
                tApren="Visual"
            } else if(conAuditivo>conVisual && conAuditivo>conKinestesico){
                tApren="Auditivo"
            } else if(conKinestesico>conVisual && conKinestesico>conAuditivo) {
                tApren = "Kinestésico"
            }
            val queue = Volley.newRequestQueue(this)
            val url = "https://synuapp2.000webhostapp.com/registerAsesorado.php"
            var jar:StringRequest =  object : StringRequest(Request.Method.POST,url,
                Response.Listener<String>{Response ->
                    Log.i("Response Success: ",Response.toString())
                    Toast.makeText(this,Response.toString(),Toast.LENGTH_LONG).show()
                },Response.ErrorListener {error->
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
                    jso["aprendizaje"] ="$tApren"
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