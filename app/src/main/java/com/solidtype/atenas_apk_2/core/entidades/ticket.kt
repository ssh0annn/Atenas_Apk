package com.solidtype.atenas_apk_2.core.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.solidtype.atenas_apk_2.core.entidades.servicio

@Entity(foreignKeys = [
    ForeignKey(entity = servicio::class, parentColumns = ["id_servicio"], childColumns = ["tipo_servicio"])
])
data class ticket (
    @PrimaryKey(autoGenerate = true) val id_ticket :Long,
    val tipo_servicio :Long,
    val codigo :Int,
    val descripcion :String,
    val subtotal :Double,
    val impuesto :Double,
    val total :Double,
    @ColumnInfo(defaultValue = "true") val estado :Boolean
)