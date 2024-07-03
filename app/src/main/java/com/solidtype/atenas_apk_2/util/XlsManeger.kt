package com.solidtype.atenas_apk_2.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.CellBase
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class XlsManeger @Inject constructor(@ApplicationContext private val context : Context) {

    fun crearXls(nombreArchivo: String, nombreColumnas: List<String>, datos: MutableList<List<String>>): Uri {
        val wb = XSSFWorkbook()
        println("Entramos en CrearXLS del XLS Maneger")

        try {
            val wbsheet = wb.createSheet()
            val filaParaNombre = wbsheet.createRow(0)

            for ((column, name) in nombreColumnas.withIndex()) {
                filaParaNombre.createCell(column).setCellValue(name)
            }

            for ((fila, dato) in datos.withIndex()) {
                val filaparaDatos = wbsheet.createRow(fila + 1)
                for ((column, name) in dato.withIndex()) {
                    filaparaDatos.createCell(column).setCellValue(name)
                }
            }

            val archivo = File(context.filesDir, "docs")
            if (!archivo.exists()) {
                archivo.mkdir()
                println("Se debe crear el archivo o la dirección")
            }

            val path = File(archivo, "$nombreArchivo.xlsx")
            val fileou = FileOutputStream(path)

            wb.write(fileou)
            fileou.close()
            wb.close()

            // Obtener URI utilizando FileProvider
            val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", path)
            println("Todo parece salir bien")
            return uri
        } catch (e: Exception) {
            println("Error en XlsManeger : $e")
        } finally {
            wb.close()
        }
        return Uri.EMPTY
    }

    suspend fun importarXlsx(path:Uri) = withContext(Dispatchers.IO){
       // val fireinput=FileInputStream(path.toFile())
        val archivo = context.contentResolver.openInputStream(path)

        val wrb=XSSFWorkbook(archivo)
        val wbs= wrb.getSheetAt(0)
        val data:MutableList<List<String>> = mutableListOf()
       try {
        for( row in wbs){
            val rowdata:MutableList<String> = mutableListOf()
            for( cell in row){
                when(cell.cellType){
                    CellType.STRING -> rowdata.add(cell.stringCellValue)
                    CellType.NUMERIC ->{
                        val numericValue = cell.numericCellValue
                        if (numericValue.isInt()) {
                            rowdata.add(numericValue.toInt().toString())
                        } else {
                            rowdata.add(numericValue.toString())
                        }
                    }
                    CellType.BOOLEAN -> rowdata.add(cell.booleanCellValue.toString())
                    CellType.BLANK -> rowdata.add("")
                    else -> rowdata.add("")
                }
            }
            data.add(rowdata)

        }
           for(i in data){
              println(i)
               println()
           }
        return@withContext data
       }catch (e:Exception){
           println("Error en lectura de excell : $e")
       }finally {
           wrb.close()
           println(" Se cerror workbook en readding excell ! ")
       }
        return@withContext data
    }

private fun Double.isInt(): Boolean {
    return this == this.toInt().toDouble()
}

}