package com.testapp.challenge.model.file

import android.graphics.Bitmap
import android.os.Environment
import com.testapp.challenge.App
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

/**
 * @author aliakseicherniakovich
 */
class StoreFileManager: FileManager {
    override fun saveBitmapIntoFile(bitmap: Bitmap): Boolean {
        var isSavedSuccess = false
        try {
            val path =  "${App.appInstance.getExternalFilesDir(Environment.DIRECTORY_DCIM)}/${Date().time}"
            val fileName = "$path.png"
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
