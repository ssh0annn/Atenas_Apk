package com.dannyhack.thermalprinter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.ImagePrintable
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Bluetooth
import com.mazenrashed.printooth.utilities.Printing

/**
 * Clase para imprimir texto en una impresora térmica
 * @param context El contexto de la aplicación.
 */
class Printer(private val context: Context) {

    // Variable para el servicio de impresión
    private var printing : Printing? = null

    // Constantes para alineación de texto
    val CENTRADA = DefaultPrinter.ALIGNMENT_CENTER
    val IZQUIERDA = DefaultPrinter.ALIGNMENT_LEFT
    val DERECHA = DefaultPrinter.ALIGNMENT_RIGHT

    // Constantes para tamaño de texto
    val NORMAL = DefaultPrinter.FONT_SIZE_NORMAL
    val GRANDE = DefaultPrinter.FONT_SIZE_LARGE

    // Constantes para modo de texto (negrita o normal)
    val NEGRITA = DefaultPrinter.EMPHASIZED_MODE_BOLD
    val NORMALITA = DefaultPrinter.EMPHASIZED_MODE_NORMAL

    // Constantes para subrayado de texto
    val SUBRAYADO = DefaultPrinter.UNDERLINED_MODE_ON
    val NOSUBRAYADO = DefaultPrinter.UNDERLINED_MODE_OFF

    // Constantes para espaciado de espacio entre líneas
    val ESPACIO_30 = DefaultPrinter.LINE_SPACING_30
    val ESPACIO_60 = DefaultPrinter.LINE_SPACING_60


    /**
     * Inicia el servicio de impresión
     */
    fun startService() {
        Printooth.init(context)

    }

    /**
     * Escanea impresoras Bluetooth disponibles
     */
    fun scanBluetoothPrinter() {
        ActivityCompat.startActivityForResult(
            context as ComponentActivity,
            Intent(context, Bluetooth::class.java),
            1,
            null
        )
    }

    /**
     * Imprime un texto en la impresora térmica
     * @param text El texto que se va a imprimir.
     * @param size El tamaño del texto; es opciona y por defecto es NORMAL.
     * @param align La alineación del texto; es opcional y por defecto es CENTRADA.
     * @param mode El modo del texto; es opcional y por defecto es NORMALITA.
     * @param underlined Si el texto debe estar subrayado; es opcional y por defecto es false.
     * @param espacioLinea El espaciado entre líneas; es opcional y por defecto es ESPACIO_30.
     * @param saltoLinea El número de saltos de línea después del texto; es opcional y por defecto es 4.
     * @return true si la impresión fue exitosa, false de lo contrario.
     */
    fun printText(
        text: String,
        size: Byte = this.NORMAL,
        align: Byte = this.CENTRADA,
        mode: Byte = this.NORMALITA,
        underlined: Boolean = false,
        espacioLinea: Byte = this.ESPACIO_30,
        saltoLinea: Int = 4,
    ): Boolean {
        if (Printooth.hasPairedPrinter()) {
            val printables = ArrayList<Printable>()
            val printable = TextPrintable.Builder()
                .setText(text)
                .setAlignment(align)
                .setFontSize(size)
                .setEmphasizedMode(mode)
                .setUnderlined(if (underlined) DefaultPrinter.UNDERLINED_MODE_ON else DefaultPrinter.UNDERLINED_MODE_OFF)
                .setLineSpacing(espacioLinea)
                .setNewLinesAfter(saltoLinea)
                .build()
            printables.add(printable)
            Printooth.printer().print(printables)
            return true
        }
        return false
    }

    /**
     * Imprime una imagen en la impresora térmica
     * @param imagen La imagen que se va a imprimir, en formato Bitmap.
     * @param align La alineación de la imagen; es opcional y por defecto es CENTRADA.
     * @param saltoLinea El número de saltos de línea después de la imagen; es opcional y por defecto es 4.
     * @return true si la impresión fue exitosa, false de lo contrario.
     */
    fun printImage(
        imagen: Bitmap,
        align: Byte = this.CENTRADA,
        saltoLinea: Int = 4,
        ) : Boolean {
        if (Printooth.hasPairedPrinter()) {
            val printables = ArrayList<Printable>()
            val printable = ImagePrintable.Builder(imagen)
                .setAlignment(align)
                .setNewLinesAfter(saltoLinea)
                .build()
            printables.add(printable)
            Printooth.printer().print(printables)
            return true
        }
        return false
    }
}