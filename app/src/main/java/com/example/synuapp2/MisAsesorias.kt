package com.example.synuapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MisAsesorias : AppCompatActivity() {
    var adapter:CustomAdapter?=null
    var nameList=ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_asesorias)

        val lis = intent.getStringArrayListExtra("asesorias") as ArrayList<String>
        lis.forEach { l->
            nameList.add(Item(l,R.drawable.curso))
        }
        adapter = CustomAdapter(nameList,this)
        var grd = findViewById<GridView>(R.id.gridViewXd3)
        grd.adapter=adapter
        grd.setOnItemClickListener{ adapterView,view,i, l->
            Toast.makeText(this,"Curso: "+nameList[l.toInt()].name,Toast.LENGTH_LONG).show()
        }
    }
}