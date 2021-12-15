package com.eldhopj.myapplication.api.queries

/**
 * Every thing api query
 *
 * @property query
 * @property sortBy
 * @constructor Create empty Every thing api query
 */
class EveryThingApiQuery(
    private val query: String? = null,
    private val sortBy: String? = null
) {
    /**
     * To map
     *
     * @return
     */
    fun toMap(): Map<String, String> {
        return mutableMapOf<String, String>().apply {
            query?.let { put("q", it) }
            sortBy?.let { put("sortBy", it) }
        }
    }
}
