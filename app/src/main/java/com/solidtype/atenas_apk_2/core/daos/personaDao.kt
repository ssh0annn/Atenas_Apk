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
    suspend fun addPersona(persona : persona)
    @Insert
    suspend fun addPersonas(persona : List<persona>)
    @Query("select * from persona")
    fun getPersonas(): Flow<List<persona>>
    @Query("select * from persona where id_persona ==:id")
    suspend fun getPersonasById(id :Int): persona
    @Update
    suspend fun updatePersona(persona: persona)
    @Delete
    suspend fun deletePersona(persona: persona)
}