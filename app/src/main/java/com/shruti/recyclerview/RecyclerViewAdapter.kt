package com.shruti.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleViewAdapter( var item: ArrayList<EntityNotes>, var activityInterface : ActivityInterface) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>()
{
 class ViewHolder ( val view : View) : RecyclerView.ViewHolder(view){
  var tvnamelist = view.findViewById<TextView>(R.id.tvnamelist)
  var tvrollnolist = view.findViewById<TextView>(R.id.tvrollnolist)
  var btupdate = view.findViewById<Button>(R.id.btupdate)
  var btdelete = view.findViewById<Button>(R.id.btdelete)
 }

 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
 // declare view //
  val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_recycle_list,parent,false)
  return ViewHolder(view)
 }

 override fun getItemCount(): Int {
 return item.size
 }

 override fun onBindViewHolder(holder: ViewHolder , position: Int) {
  holder.tvnamelist.setText(item[position].title)
  holder.tvrollnolist.setText(item[position].description.toString())
  holder.btupdate.setOnClickListener {
   activityInterface.updateClick(item[position],position)
  }
  holder.btdelete.setOnClickListener {
   activityInterface.deleteClick(item[position],position)
  }
 }
}




