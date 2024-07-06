package com.solidtype.atenas_apk_2.products.data.repositoryImpl

import android.net.Uri
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.gestion_proveedores.data.personaDao
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder.PersonaBuilder
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util.client_builder.PersonaDirector
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.products.data.local.dao.categoriaDao
import com.solidtype.atenas_apk_2.products.data.local.dao.inventarioDao
import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.interfaces.MediatorProducts
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import com.solidtype.atenas_apk_2.util.XlsManeger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class InventarioRepoImpl @Inject constructor(
    private val catego: categoriaDao,
    private val daoProductos: inventarioDao,
    private val excel: XlsManeger,
    private val mediador2: MediatorProducts,
    private val proDao: personaDao
) : InventarioRepo {


    override fun getProducts(): Flow<List<inventario>> {

        return daoProductos.getInventario()

    }

    override suspend fun getProductByCodigo(codigo: Int): inventario {
        return daoProductos.getInventariosById(codigo)
    }

    override fun searchProductsLike(datos: String): Flow<List<inventario>> {
        return daoProductos.buscarEnInventario(datos)
    }

    override suspend fun createProducts(prodcuto: inventario): Boolean {
        daoProductos.addInventario(prodcuto)
        return true
    }

    override suspend fun deleteProduct(codigo: inventario): Boolean {
        daoProductos.deleteInventario(codigo)
        return true//hay que manejarlo
    }

    override suspend fun updateProduct(producto: inventario): Boolean {
        daoProductos.updateInventario(producto)
        return true //hay que manejarlo

    }

    override suspend fun exportarExcel(productos: List<inventario>): Uri =
        withContext(Dispatchers.Default) {

            val columnas = listOf(
                "categoria",
                "proveedor",
                "nombre",
                "marca",
                "modelo",
                "cantidad",
                "precio_compra",
                "precio_venta",
                "impuesto",
                "descripcion",
            )
            val datos: MutableList<List<String>> = mutableListOf()

            try {

                for (i in productos) {

                    val rowdata = mutableListOf<String>()
                    rowdata.add(catego.getCategoriasById(i.id_categoria).nombre)
                    rowdata.add(proDao.getPersonasById(i.id_proveedor!!).nombre!!)
                    rowdata.add(i.nombre)
                    rowdata.add(i.marca ?: "")
                    rowdata.add(i.modelo.toString())
                    rowdata.add(i.cantidad.toString())
                    rowdata.add(i.precio_compra.toString())
                    rowdata.add(i.precio_venta.toString())
                    rowdata.add(i.impuesto.toString())
                    rowdata.add(i.descripcion.toString())
                    datos.add(rowdata)
                }


            } catch (e: Exception) {
                println("Error en el repositorio recorriendo el flow")
            }

            return@withContext excel.crearXls(
                "AtenasInventario${System.currentTimeMillis()}",
                columnas,
                datos
            )
        }
    private suspend fun buscaCreaCategoria(categoria: String): categoria = withContext(Dispatchers.IO) {
        // Paso 1: Recolectar la lista de categorías
        val listaCate = catego.buscarCategorias(categoria.lowercase()).first()

        // Paso 2: Buscar la categoría en la lista recolectada
        var categoEncontrada = listaCate.find {
            it.nombre.lowercase() == categoria.lowercase()
        }

        // Paso 3: Si no se encuentra, agregar la categoría y volver a buscar
        if (categoEncontrada == null) {
            catego.addCategoria(
                categoria(
                    id_categoria = System.currentTimeMillis(),
                    nombre = categoria,
                    descripcion = "Aun no definida",
                    estado = true
                )
            )
            // Recolectar la lista de nuevo después de agregar la categoría
            val nuevaListaCate = catego.buscarCategorias(categoria.lowercase()).first()
            categoEncontrada = nuevaListaCate.find {
                it.nombre.lowercase() == categoria.lowercase()
            }
            println("NO se encontro categoria, agregando $categoEncontrada")
        }

        println("NO se pudo encontrar, debio de agregarse $categoEncontrada")
        return@withContext categoEncontrada as categoria
    }

    private suspend fun buscaCreaProveedor(proveedor: String): persona = withContext(Dispatchers.IO) {
        println("Buscando proveedor : $proveedor")
        // Paso 1: Recolectar la lista de proveedores
        val listaProveedor = proDao.getPersonasTipo("proveedor", proveedor.lowercase()).first()

        // Paso 2: Buscar el proveedor en la lista recolectada
        var proveedorEncontrada = listaProveedor.find {
            it.nombre?.lowercase() == proveedor.lowercase()
        }

        // Paso 3: Si no se encuentra, agregar el proveedor y volver a buscar
        if (proveedorEncontrada == null) {
            proDao.addPersona(
                PersonaDirector.createPersona(
                    Personastodas.Proveedor(
                        id_proveedor = System.currentTimeMillis(),
                        nombre = proveedor,
                        tipo_documento = null,
                        documento = null,
                        direccion = null,
                        telefono = null,
                        email = null
                    )
                )
            )
            println("NO se encontro proveedor, agregando $proveedorEncontrada")
            // Recolectar la lista de nuevo después de agregar el proveedor
            val nuevaListaProveedor = proDao.getPersonasTipo("proveedor", proveedor.lowercase()).first()
            proveedorEncontrada = nuevaListaProveedor.find {
                it.nombre?.lowercase() == proveedor.lowercase()
            }
        }
        println("Se encontro proveedor $proveedorEncontrada")

        println("NO se pudo encontrar, debio de agregarse $proveedorEncontrada")
        return@withContext proveedorEncontrada as persona
    }

    override suspend fun importarExcel(path: Uri): Boolean {
//Manejar excepcion de validacin de datos.
       return withContext(Dispatchers.IO){
           val listaProductos: MutableList<inventario> = mutableListOf()
           val datos = async{excel.importarXlsx(path)}.await()
            if (validarNombresColumnas(datos[0])) {
                try {
                    for ((index, i) in datos.withIndex()) {
                        if (index > 0) {

                            val categoid =  async{buscaCreaCategoria(i[0]).id_categoria}.await()
                            val proveeID = async{buscaCreaProveedor(i[1]).id_persona}.await()
                            println("Id Proveedor $proveeID")
                            println("ID categoria $categoid")
                            listaProductos.add(
                                inventario(
                                    id_categoria = categoid,
                                    id_proveedor = proveeID,
                                    nombre = i[2],
                                    marca = i[3],
                                    modelo = i[4],
                                    cantidad = i[5].toInt(),
                                    precio_compra = i[6].toPreciseDouble(),
                                    precio_venta = i[7].toPreciseDouble(),
                                    impuesto = i[8].toPreciseDouble(),
                                    descripcion = i[9],
                                    estado = true
                                )
                            )
                        }
                    }
                    println("Listos para insertar: ${listaProductos}")
                   async {  daoProductos.addInventarios(listaProductos)}.await()
                    println("Insertando datos !!...$datos")
                    return@withContext true
                } catch (e: Exception) {
                    println("Error importando excel, ver formato: $e")
                }
            }

            return@withContext false
        }

    }

    override suspend fun syncronizacionProductos() {
        mediador2()
    }

    override fun getCategorias(): Flow<List<categoria>> {
        return catego.getCategorias()
    }

    override suspend fun agregarCategorias(categoria: categoria) {
        catego.addCategoria(categoria)
    }

    override suspend fun eliminarCategoria(categoria: categoria) {
        catego.deleteCategoria(categoria)
    }

    override suspend fun buscarCategorias(any: String): Flow<List<categoria>> {
        return catego.buscarCategorias(any)
    }

}

private fun validarNombresColumnas(columnas: List<String?>): Boolean {
    println("Nombre de columnas: $columnas")
    val nombresOrigin = listOf(
        "categoria",
        "proveedor",
        "nombre",
        "marca",
        "modelo",
        "cantidad",
        "costo",
        "precio",
        "impuesto",
        "descripcion"
    )

    if (columnas.isNotEmpty()) {
        val listaTEmpo: MutableList<String> = mutableListOf()
        for (i in columnas) {
            if (i!!.isNotBlank()) {
                listaTEmpo.add(i)
            }
        }
        println("Nueva listaTEmpo: $listaTEmpo")
        if (nombresOrigin == listaTEmpo) {
            println("Comparacion de listas : ${nombresOrigin == listaTEmpo}")
            return true
        }
    }
    println("Los datos son incorrectos... verifica")


    return false

}

//Magia
fun String.toPreciseDouble(): Double {
    return try {
        BigDecimal(this).setScale(2, RoundingMode.HALF_UP).toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }
}