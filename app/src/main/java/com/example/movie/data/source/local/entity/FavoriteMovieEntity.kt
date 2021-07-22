package com.example.movie.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movie.utils.database.DbConstant
import org.jetbrains.annotations.NotNull

@Entity(tableName = DbConstant.ENTITY_FAVORITE_MOVIE)
data class FavoriteMovieEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null
)