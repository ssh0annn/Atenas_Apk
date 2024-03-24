package com.solidtype.atenas_apk_2.util

import android.os.Environment
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

class XlsManeger @Inject constructor() {

   suspend fun crearXls(nombreArchivo:String, nombreColumnas:List<String>, datos:MutableList<List<String?>>):String? =withContext(Dispatchers.IO){
        val wb=XSSFWorkbook()

       try {
             val wbsheet= wb.createSheet()
             val filaParaNombre=   wbsheet.createRow(0)
               for((column, name) in nombreColumnas.withIndex()){//nombro las columnas principales
                   filaParaNombre.createCell(column).setCellValue(name)

            }

            for ((fila, dato) in datos.withIndex()){ //relleno el cuerpo con los datos.
                val filaparaDatos=wbsheet.createRow(fila+1)
                for((column, name) in dato.withIndex()){
                    filaparaDatos.createCell(column).setCellValue(name)

                }
            }
            val external = Environment.getExternalStorageDirectory().path + File.separator + "Documents"
            println("Este es el external $external")
            val path =  external+ File.separator + nombreArchivo + ".xlsx"
            val archivo = File(external)
            if(!archivo.exists()){
                archivo.mkdir()
                println("Se debe crear el archivo o la direccion")
            }


            val fileOuput= FileOutputStream(path)

            wb.write(fileOuput)
            println("Todo parece salir bien")
           return@withContext path
        }catch (e:Exception){
            println("Error en XlsManeger : $e")
        }finally {
            wb.close()
        }
       return@withContext null
    }

    suspend fun importarXlsx(path:String):List<List<String>> = withContext(Dispatchers.IO){
        val fireinput=FileInputStream(path)
        val wrb=XSSFWorkbook(fireinput)
        val wbs= wrb.getSheetAt(0)
        var data:MutableList<List<String>> = mutableListOf()
       try {


        for( row in wbs){
            val rowdata:MutableList<String> = mutableListOf()
            for( cell in row){
                when(cell.cellType){
                    CellType.STRING -> rowdata.add(cell.stringCellValue)
                    CellType.NUMERIC -> rowdata.add(cell.numericCellValue.toString())
                    CellType.BOOLEAN -> rowdata.add(cell.booleanCellValue.toString())
                    CellType.BLANK -> rowdata.add("")
                    else -> rowdata.add("")
                }
            }
            data.add(rowdata)
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





}