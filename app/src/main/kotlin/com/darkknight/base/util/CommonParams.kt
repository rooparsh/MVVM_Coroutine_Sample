package com.darkknight.base.util

/**
 *
 * Created by Rooparsh Kalia on 11/04/20
 *
 **/
class CommonParams
/**
 * Constructor
 *
 * @param builder object of builder class of CommonParams
 */
private constructor(builder: Builder) {


    var map: HashMap<String, String>

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
        internal val map = HashMap<String, String>()

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
            map[key] = value.toString()
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
        fun add(key: String, value: Any?, isAllowEmpty: Boolean): Builder {
            if (!isAllowEmpty && (value == null || value.toString().isEmpty())) {
                return this
            }
            map[key] = value.toString()
            return this
        }


        /**
         * Build common params.
         *
         * @return the common params
         */
        fun build(): CommonParams {
            return CommonParams(this)
        }
    }
}
