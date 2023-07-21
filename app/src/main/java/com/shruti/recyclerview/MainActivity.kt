package com.shrutix.recyclerview

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shruti.recyclerview.ActivityInterface
import com.shruti.recyclerview.EntityNotes
import com.shruti.recyclerview.Notesdb
import com.shruti.recyclerview.RecycleViewAdapter
import com.shruti.recyclerview.databinding.ActivityMainBinding
import com.shruti.recyclerview.databinding.CustomdialogBinding

class MainActivity() : AppCompatActivity(),ActivityInterface{
    lateinit var binding : ActivityMainBinding//declare binding//
    lateinit var recyclerViewAdapter: RecycleViewAdapter
    lateinit var gridLayout: GridLayoutManager
    lateinit var layoutManager : LinearLayoutManager
    lateinit var notesdb: Notesdb
    var item = arrayListOf<EntityNotes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)//initialize binding//
        setContentView(binding.root)
        recyclerViewAdapter = RecycleViewAdapter(item,this)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        gridLayout = GridLayoutManager(this,2)
        binding.recycleview.layoutManager = gridLayout
        binding.recycleview.adapter = recyclerViewAdapter
        notesdb = Notesdb.getDatabase(this)
        binding.fab.setOnClickListener {
            var dialog = Dialog(this)
            var dialogbinding = CustomdialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogbinding.root)
            dialogbinding.btclick.setOnClickListener {
                if (dialogbinding.ettitle.text.toString().isNullOrEmpty()) {
                    dialogbinding.ettitle.error = "Enter title"
                } else if (dialogbinding.etdescp.text.toString().isNullOrEmpty()) {
                    dialogbinding.etdescp.error = "Enter description"
                } else {
                    /*item.add(
                        EntityNotes(
                           title= dialogbinding.ettitle.text.toString(),
                            description = dialogbinding.etdescp.text.toString()
                        )
                    )*/
                    class insert :AsyncTask<Void,Void,Void>(){
                        override fun doInBackground(vararg p0: Void?): Void? {
                            notesdb.notesinterface().insertNotes( EntityNotes(
                                title= dialogbinding.ettitle.text.toString(),
                                description = dialogbinding.etdescp.text.toString()
                            ))
                            return null
                        }

                        override fun onPostExecute(result: Void?) {
                            super.onPostExecute(result)
                            getNotes()
                        }


                    }
                    insert().execute()
                    dialog.dismiss()
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }
            dialog.show()
        }
        getNotes()
    }
    fun getNotes(){
        item.clear()
     class getNotes : AsyncTask<Void,Void,Void>(){
         override fun doInBackground(vararg p0: Void?): Void? {
             item.addAll(notesdb.notesinterface().getNotes())
             return null
         }

         override fun onPostExecute(result: Void?) {
             super.onPostExecute(result)
             recyclerViewAdapter.notifyDataSetChanged()
         }
     }
        getNotes().execute()
    }

    override fun updateClick(student: EntityNotes, position: Int) {
        var dialog = Dialog(this)
        var dialogbinding = CustomdialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogbinding.root)

        dialogbinding.btclick.setOnClickListener {
            if (dialogbinding.ettitle.text.toString().isNullOrEmpty()) {
                dialogbinding.ettitle.error = "Enter title"
            } else if (dialogbinding.etdescp.text.toString().isNullOrEmpty()) {
                dialogbinding.etdescp.error = "Enter description"
            } else {
                item.set(position,
                    EntityNotes(title= dialogbinding.ettitle.text.toString(), description = dialogbinding.etdescp.text.toString()))
                class update : AsyncTask<Void,Void,Void>(){
                    override fun doInBackground(vararg p0: Void?): Void ?{
                        notesdb.notesinterface().updateNotes(
                            EntityNotes(
                                id= item[position].id,
                                title= dialogbinding.ettitle.text.toString(), description = dialogbinding.etdescp.text.toString())
                        )
                        return null
                    }

                    override fun onPostExecute(result: Void?) {
                        super.onPostExecute(result)
                        getNotes()
                    }

                }
                update().execute()
                dialog.dismiss()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
        dialog.show()
    }

    override fun deleteClick(student: EntityNotes, position: Int) {
      //  item.removeAt(position)//
        class delete : AsyncTask<Void,Void,Void>(){
          override fun doInBackground(vararg p0: Void?): Void ?{
              notesdb.notesinterface().delNotes(item[position])
              return null
          }

          override fun onPostExecute(result: Void?) {
              super.onPostExecute(result)
              getNotes()
          }
      }
        delete().execute()
        recyclerViewAdapter.notifyDataSetChanged()
    }
}