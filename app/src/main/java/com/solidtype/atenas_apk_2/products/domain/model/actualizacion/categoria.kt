package com.solidtype.atenas_apk_2.products.domain.model.actualizacion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class categoria (
    @PrimaryKey(autoGenerate = true) val id_categoria :Long = 1000,
    @ColumnInfo val nombre :String,
    @ColumnInfo val descripcion :String?,
    @ColumnInfo(defaultValue = "true") var estado :Boolean
){
    // Constructor sin argumentos necesario para Firestore
    constructor() : this(
        id_categoria = 0,
        nombre = "",
        descripcion = null,
        estado = true
    )
}