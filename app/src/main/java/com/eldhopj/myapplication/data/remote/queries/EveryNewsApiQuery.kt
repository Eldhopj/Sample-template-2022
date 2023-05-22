package com.eldhopj.myapplication.data.remote.queries

/**
 * Every thing api query
 *
 * @property query
 * @property sortBy
 * @constructor Create empty Every thing api query
 */
class EveryNewsApiQuery(
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
