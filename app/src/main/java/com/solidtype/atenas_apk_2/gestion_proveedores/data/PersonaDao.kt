package com.solidtype.atenas_apk_2.gestion_proveedores.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface personaDao {
    @Insert
    suspend fun addPersona(persona : persona)
    @Insert
   suspend  fun addPersonas(persona : List<persona>)
    @Query("select * from persona")
    fun getPersonas(): Flow<List<persona>>

    @Query("select * from persona where tipo_persona == LOWER(:tipoPersona)")
    fun getPersonasTipo(tipoPersona:String): Flow<List<persona>>

    @Query("""select * from persona where tipo_persona == :tipo AND id_persona LIKE '%' || LOWER(:any) || '%'
             OR nombre LIKE '%' || LOWER(:any) || '%'
             OR tipo_documento LIKE '%' || LOWER(:any) || '%'
             OR email LIKE '%' || LOWER(:any) || '%'
            """
    )
    fun getPersonasTipo(tipo:String, any:String): Flow<List<persona>>

    @Query("select * from persona where id_persona ==:id")
    suspend fun getPersonasById(id :Int): persona
    @Update
    suspend fun updatePersona(persona: persona)
    @Delete
    suspend fun deletePersona(persona: persona)

}