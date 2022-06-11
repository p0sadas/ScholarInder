package com.example.synuapp2
import android.content.Intent
import com.example.synuapp2.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import android.widget.Toast
import org.json.JSONObject

class NuevoCursoAsesorado : AppCompatActivity() {
    var adapter:CustomAdapter?=null
    var nameList=ArrayList<Item>()
    var idAses = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_curso_asesorado)
        val lis = intent.getStringArrayListExtra("asesorias") as ArrayList<String>
        val luas = intent.getStringArrayListExtra("uas") as ArrayList<String>
        val asesorado = intent.getStringExtra("res")
        val ases = intent.getStringArrayListExtra("asesores")
        lis.forEach { l->
            ases!!.forEach { ua ->
                if(JSONObject(ua).getString("boleta") == JSONObject(l).getString("boletaAsesor")){
                    luas.forEach{lua ->
                        if (JSONObject(lua).getString("idUa")==JSONObject(l).getString("idUa") && JSONObject(asesorado).getString("aprendizaje") == JSONObject(ua).getString("aprendizaje")){
                            nameList.add(Item(JSONObject(lua).getString("name").toString(),R.drawable.curso))
                        }
                    }
                }
            }

        }
        adapter = CustomAdapter(nameList,this)
        var grd = findViewById<GridView>(R.id.gridViewXd)
        grd.adapter=adapter
        var res = JSONObject(intent.getStringExtra("res")).toString()
        grd.setOnItemClickListener{ adapterView,view,i, l->
            Toast.makeText(this,"Curso: "+nameList[l.toInt()].name,Toast.LENGTH_LONG).show()
            val intent = Intent(this,NuevoCursoAsesoradoSig::class.java)
            intent.putExtra("curso",nameList[l.toInt()].name)
            intent.putExtra("asesorado",res)
            var idU=""
            var nameList2 = ArrayList<String>()
            luas.forEach { lua->
                if(JSONObject(lua).getString("name")==nameList[l.toInt()].name){
                    idU=JSONObject(lua).getString("idUa")
                }
            }
            Log.i("idUa",idU)
            lis.forEach { l->
               ases!!.forEach { ua ->
                    if(JSONObject(ua).getString("boleta") == JSONObject(l).getString("boletaAsesor")){
                            if (idU==JSONObject(l).getString("idUa") && JSONObject(asesorado).getString("aprendizaje") == JSONObject(ua).getString("aprendizaje")){
                                nameList2.add(ua)
                            }
                    }
                }

            }
            Log.i("paaa",nameList2.toString())
            intent.putExtra("idCurso",idU.toInt())
            intent.putExtra("ppaa",nameList2)
            startActivity(intent)
        }
    }
}