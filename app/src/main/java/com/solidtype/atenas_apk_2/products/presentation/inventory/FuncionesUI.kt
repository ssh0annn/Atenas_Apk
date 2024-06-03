package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.app.Activity
import android.content.Context
import android.content.Intent

fun showFilePickers(context: Context) {

    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "*/*"
        putExtra(
            Intent.EXTRA_MIME_TYPES, arrayOf(
                "application/vnd.ms-excel",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            )
        )
    }

    (context as? Activity)?.startActivityForResult(intent, 2)
}