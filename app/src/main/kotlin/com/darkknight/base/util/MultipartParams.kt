package com.darkknight.base.util

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

/**
 *
 * Created by Rooparsh Kalia on 11/04/20
 *
 **/
class MultipartParams
/**
 * Constructor
 *
 * @param builder object of builder class of CommonParams
 */
private constructor(builder: Builder) {


    var map: HashMap<String, RequestBody>

    init {
        this.map = builder.map
    }

    /**
     * Instantiates a new Builder.
     */
    class Builder {
        /**
         * The Map.
         */
        internal val map = HashMap<String, RequestBody>()

        /**
         * Add a value to the map.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        fun add(key: String, value: Any?): Builder {
            if (value == null || value.toString().isEmpty()) {
                return this
            }
            map[key] = RetrofitUtils.getRequestBodyFromString(value.toString())
            return this
        }

        /**
         * Add a value to the map
         *
         * @param key          the key
         * @param value        the value
         * @param isAllowEmpty is empty allowed
         * @return the builder
         */
        fun add(key: String, value: Any?, isAllowEmpty: Boolean = false): Builder {
            if (isAllowEmpty.not() and (value == null || value.toString().isEmpty())) {
                return this
            }
            map[key] = RetrofitUtils.getRequestBodyFromString(value.toString())
            return this
        }

        fun addFile(key: String, file: File?): Builder {
            if (file == null) {
                return this
            }

            map[key + "\";filename=\" ${file.name}"] =
                file.asRequestBody(RetrofitUtils.getMimeType(file).toMediaType())
            return this
        }


        fun addArrayOfFiles(key: String, files: ArrayList<File>?): Builder {
            if (files == null || files.size == 0) {
                return this
            }

            files.forEach { file ->
                map[key + "\";filename=\" ${file.name}"] =
                    file.asRequestBody(RetrofitUtils.getMimeType(file).toMediaType())
            }

            return this
        }


        /**
         * Build common params.
         *
         * @return the common params
         */
        fun build(): MultipartParams {
            return MultipartParams(this)
        }
    }
}
