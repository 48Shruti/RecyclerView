package com.shruti.recyclerview

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesInterface {
    @Insert
    fun insertNotes(entityNotes: EntityNotes)

    @Query("Select * from Entitynotes")
    fun getNotes() : List<EntityNotes>

    @Delete
    fun delNotes(entityNotes: EntityNotes)

    @Update
    fun updateNotes(entityNotes: EntityNotes)
}