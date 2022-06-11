package com.example.synuapp2
import com.example.synuapp2.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class CustomAdapter:BaseAdapter{

    var nameList = ArrayList<Item>()
    var context: Context? = null


    constructor(nameList: ArrayList<Item>,context: Context?) : super(){
        this.nameList = nameList
        this.context = context
    }

    override fun getCount(): Int {
        return  nameList.size
    }

    override fun getItem(position: Int): Any {
        return  nameList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val nameGridList = this.nameList[position]
        var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var nameView=inflator.inflate(R.layout.item,null)
        nameView.findViewById<ImageView>(R.id.imageCard).setImageResource(nameGridList.image!!)
        nameView.findViewById<TextView>(R.id.txtCard).text = nameGridList.name!!
        return  nameView
    }

}