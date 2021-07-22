package com.example.movie.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movie.utils.database.DbConstant

@Entity(tableName = DbConstant.ENTITY_FAVORITE_SHOW)
data class FavoriteShowEntity(
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String? = null,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null
)
