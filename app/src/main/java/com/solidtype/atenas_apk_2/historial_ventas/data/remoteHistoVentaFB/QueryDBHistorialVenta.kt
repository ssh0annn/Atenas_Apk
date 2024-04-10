package com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB

import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialVentaDAO
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import javax.inject.Inject

class QueryDBHistorialVenta @Inject constructor(
    private val dao: HistorialVentaDAO
) {

    /**
     * @param: List<String>
     * @return: Data Object
     * @throws: Exception si no es compatible los datos con el objeto espesificado.
     * @funcionamiento: Es para uso interno, para convertir una lista de strings en datos adecuados para el objeto HistorialVentaEntidad
     * Favor verificar el formato de este objeto antes de someter la lista.
     * Los elementos deben ser igual a 13.
     */
    private fun entityConvert(it:List<String>) :HistorialVentaEntidad{
        if (it.size == 13){
            try {
                return HistorialVentaEntidad(
                    Codigo = it[0].toInt(),
                    Nombre = it[1],
                    Descripcion=it[3],
                    Cantidad = it[5].toInt(),
                    Categoria = it[6],
                    Modelo = it[7],
                    Marca =  it[8],
                    Precio = it[9].toDouble(),
                    TipoVenta = it[10],
                    Total = it[11].toDouble(),
                    FechaIni = it[12],
                    FechaFin = it[13]
                )
            }catch (e:Exception){
                println("Este es la razon lista: $it, size ${it.size}")
                throw Exception("El tipo de la lista no es compatible con la HistorialVentaEntidad $e")
            }


        }
        throw Exception("El tamaño de la lista entregada no es compatible")
    }

    /**
     * @param  List<ProductEntity>
     * @return List<List<String>>
     * @funcionamiento Funcion de uso interno para convertir una entidad de tipo HistorialVentaEntidad en una lista de lista de Strings.
     * Favor ver la data class HistorialVentaEntidad.
     */
/*
    private fun entityToListString(data: List<HistorialVentaEntidad>): List<List<String>>{
        val mutableListData : MutableList<List<String>> = mutableListOf()
        if (data.isNotEmpty()){
            data.forEach {

            }
        }
    }

*/

}
