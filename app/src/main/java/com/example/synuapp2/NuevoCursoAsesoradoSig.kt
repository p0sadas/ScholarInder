package com.example.synuapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import android.widget.Toast
import org.json.JSONObject

class NuevoCursoAsesoradoSig : AppCompatActivity() {
    var adapter:CustomAdapter?=null
    var nameList=ArrayList<Item>()
    var boletaasesor=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_curso_asesorado_sig)
        var nameListPassed = intent.getStringArrayListExtra("ppaa") as ArrayList<String>
        nameListPassed.forEach { nlp->
            nameList.add(Item(JSONObject(nlp).getString("name"),R.drawable.user))
        }
        adapter = CustomAdapter(nameList,this)
        var grd = findViewById<GridView>(R.id.gridViewXd2)
        grd.adapter=adapter
        var asesorado = JSONObject(intent.getStringExtra("asesorado")).toString()
        var curso = intent.getStringExtra("curso")
        var idCurso = intent.getIntExtra("idCurso",0)
        grd.setOnItemClickListener{ adapterView,view,i, l->
            Toast.makeText(this,"Asesor: "+nameList[l.toInt()].name, Toast.LENGTH_LONG).show()
            val intent = Intent(this,RevirsarFinalAsesorado::class.java)
            intent.putExtra("curso",curso)
            intent.putExtra("idCurso",idCurso)
            intent.putExtra("asesor",nameList[l.toInt()].name)
            nameListPassed.forEach { ase->
                if (nameList[l.toInt()].name==JSONObject(ase).getString("name")){
                    boletaasesor = JSONObject(ase).getString("boleta")
                }
            }
            intent.putExtra("idAsesor",boletaasesor)
            intent.putExtra("asesorado",asesorado)
            startActivity(intent)
        }
    }
}