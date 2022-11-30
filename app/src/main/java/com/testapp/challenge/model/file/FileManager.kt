package com.testapp.challenge.model.file

import android.graphics.Bitmap

/**
 * @author aliakseicherniakovich
 */
interface FileManager {
    fun saveBitmapIntoFile(bitmap: Bitmap): Boolean
}
