package com.solidtype.atenas_apk_2.gestion_proveedores.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface personaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersona(persona : persona)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend  fun addPersonas(persona : List<persona>)
    @Query("select * from persona")
    fun getPersonas(): Flow<List<persona>>

    @Query("select * from persona where tipo_persona == LOWER(:tipoPersona)")
    fun getPersonasTipo(tipoPersona:String): Flow<List<persona>>

    @Query("""select * from persona where id_persona LIKE '%' || :any|| '%'
             OR email LIKE '%' || :any|| '%'
             OR nombre LIKE '%' || :any || '%'
             OR tipo_documento LIKE '%' || :any || '%' 
             AND ( tipo_persona == :tipo)
           
            """
    )
    fun getPersonasTipo(tipo:String, any:String): Flow<List<persona>>

    @Query("select * from persona where id_persona ==:id")
    suspend fun getPersonasById(id :Int): persona
    @Update
    suspend fun updatePersona(persona: persona)
    @Transaction
    suspend fun deletePersona(persona: persona){
        persona.estado = false
        updatePersona(persona)
    }

}