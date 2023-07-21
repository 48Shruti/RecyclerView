package com.shruti.recyclerview

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1 , entities = [EntityNotes::class])
 abstract class Notesdb  : RoomDatabase(){
    abstract fun notesinterface (): NotesInterface
    companion object {
        var notesdb : Notesdb ?= null
        fun getDatabase (context:Context): Notesdb{
            if(notesdb == null){
                notesdb = Room.databaseBuilder(context,Notesdb::class.java, context.resources.getString(R.string.app_name)).build()
            }
            return notesdb!!
        }
    }
}