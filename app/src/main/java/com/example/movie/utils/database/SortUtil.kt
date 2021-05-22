package com.example.movie.utils.database

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtil {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"

    fun getSortedQuery(entity: String, column: String, filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $entity ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY $column DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY $column ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}