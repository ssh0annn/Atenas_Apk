package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.persona
import kotlinx.coroutines.flow.Flow

@Dao
interface personaDao {
    @Insert
    fun addPersona(persona : persona)
    @Insert
    fun addPersonas(persona : List<persona>)
    @Query("select * from persona")
    fun getPersonas(): Flow<List<persona>>
    @Query("select * from persona where id_persona ==:id")
    fun getPersonasById(id :Int): persona
    @Update
    fun updatePersona(persona: persona)
    @Delete
    fun deletePersona(persona: persona)
}