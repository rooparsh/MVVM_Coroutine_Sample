package com.darkknight.base.util

import android.net.Uri
import android.webkit.MimeTypeMap
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import java.io.File

/**
 *
 * Created by Rooparsh Kalia on 11/04/20
 *
 **/
object RetrofitUtils {

    fun getRequestBodyFromString(value: String): RequestBody =
        value.toRequestBody("text/plain".toMediaType())

    fun getRequestBodyFromFile(key: String, file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody(getMimeType(file).toMediaType())
        return MultipartBody.Part.createFormData(key, file.name, requestFile)
    }

    fun getMimeType(file: File): String {
        var mimeType = "image/png"
        try {
            val selectedUri = Uri.fromFile(file)
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(selectedUri.toString())
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension)!!
        } catch (e: Exception) {
            Timber.e(e.toString())
        }

        return mimeType

    }
}