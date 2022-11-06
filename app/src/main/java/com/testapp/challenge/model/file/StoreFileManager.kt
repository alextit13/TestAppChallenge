package com.testapp.challenge.model.file

import android.graphics.Bitmap
import android.os.Environment
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

/**
 * @author aliakseicherniakovich
 */
class StoreFileManager {
    fun saveBitmapIntoFile(bitmap: Bitmap): Boolean {
        var isSavedSuccess = false
        try {
            val path = Environment.getExternalStorageDirectory().toString()
            bitmap.setHasAlpha(true)
            val fileName = "$path/${Date().time}.png"
            FileOutputStream(fileName).use { out ->
                bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    out
                )
            }
            isSavedSuccess = true
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return isSavedSuccess
    }
}
