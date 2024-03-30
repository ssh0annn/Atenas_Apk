package com.solidtype.atenas_apk_2.products.presentation.inventory.prueba

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import androidx.core.app.ActivityCompat.startActivityForResult

class TesteandoIntent {

    // Request code for selecting a PDF document.
    val PICK_PDF_FILE = 2

    fun openFile(pickerInitialUri: Uri, context: Context) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"

            // Optionally, specify a URI for the file that should appear in the
            // system file picker when it loads.
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
        }

        (context as Activity).startActivityForResult(intent, PICK_PDF_FILE)
    }

}