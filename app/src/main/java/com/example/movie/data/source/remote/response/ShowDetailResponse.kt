package com.example.movie.data.source.remote.response


import com.squareup.moshi.Json

data class ShowDetailResponse(
    @field:Json(name = "backdrop_path")
    val backdropPath: String? = null,
    @field:Json(name = "created_by")
    val createdBy: List<CreatedBy?>? = null,
    @field:Json(name = "episode_run_time")
    val episodeRunTime: List<Int?>? = null,
    @field:Json(name = "first_air_date")
    val firstAirDate: String? = null,
    @field:Json(name = "genres")
    val genres: List<Genre?>? = null,
    @field:Json(name = "homepage")
    val homepage: String? = null,
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "in_production")
    val inProduction: Boolean? = null,
    @field:Json(name = "languages")
    val languages: List<String?>? = null,
    @field:Json(name = "last_air_date")
    val lastAirDate: String? = null,
    @field:Json(name = "last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "networks")
    val networks: List<Network?>? = null,
    @field:Json(name = "next_episode_to_air")
    val nextEpisodeToAir: Any? = null,
    @field:Json(name = "number_of_episodes")
    val numberOfEpisodes: Int? = null,
    @field:Json(name = "number_of_seasons")
    val numberOfSeasons: Int? = null,
    @field:Json(name = "origin_country")
    val originCountry: List<String?>? = null,
    @field:Json(name = "original_language")
    val originalLanguage: String? = null,
    @field:Json(name = "original_name")
    val originalName: String? = null,
    @field:Json(name = "overview")
    val overview: String? = null,
    @field:Json(name = "popularity")
    val popularity: Float? = null,
    @field:Json(name = "poster_path")
    val posterPath: String? = null,
    @field:Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany?>? = null,
    @field:Json(name = "production_countries")
    val productionCountries: List<ProductionCountry?>? = null,
    @field:Json(name = "seasons")
    val seasons: List<Season?>? = null,
    @field:Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>? = null,
    @field:Json(name = "status")
    val status: String? = null,
    @field:Json(name = "tagline")
    val tagline: String? = null,
    @field:Json(name = "type")
    val type: String? = null,
    @field:Json(name = "vote_average")
    val voteAverage: Float? = null,
    @field:Json(name = "vote_count")
    val voteCount: Int? = null
) {
    data class CreatedBy(
        @field:Json(name = "credit_id")
        val creditId: String? = null,
        @field:Json(name = "gender")
        val gender: Int? = null,
        @field:Json(name = "id")
        val id: Int? = null,
        @field:Json(name = "name")
        val name: String? = null,
        @field:Json(name = "profile_path")
        val profilePath: String? = null
    )

    data class Genre(
        @field:Json(name = "id")
        val id: Int? = null,
        @field:Json(name = "name")
        val name: String? = null
    )

    data class LastEpisodeToAir(
        @field:Json(name = "air_date")
        val airDate: String? = null,
        @field:Json(name = "episode_number")
        val episodeNumber: Int? = null,
        @field:Json(name = "id")
        val id: Int? = null,
        @field:Json(name = "name")
        val name: String? = null,
        @field:Json(name = "overview")
        val overview: String? = null,
        @field:Json(name = "production_code")
        val productionCode: String? = null,
        @field:Json(name = "season_number")
        val seasonNumber: Int? = null,
        @field:Json(name = "still_path")
        val stillPath: String? = null,
        @field:Json(name = "vote_average")
        val voteAverage: Float? = null,
        @field:Json(name = "vote_count")
        val voteCount: Int? = null
    )

    data class Network(
        @field:Json(name = "id")
        val id: Int? = null,
        @field:Json(name = "logo_path")
        val logoPath: String? = null,
        @field:Json(name = "name")
        val name: String? = null,
        @field:Json(name = "origin_country")
        val originCountry: String? = null
    )

    data class ProductionCompany(
        @field:Json(name = "id")
        val id: Int? = null,
        @field:Json(name = "logo_path")
        val logoPath: String? = null,
        @field:Json(name = "name")
        val name: String? = null,
        @field:Json(name = "origin_country")
        val originCountry: String? = null
    )

    data class ProductionCountry(
        @field:Json(name = "iso_3166_1")
        val iso31661: String? = null,
        @field:Json(name = "name")
        val name: String? = null
    )

    data class Season(
        @field:Json(name = "air_date")
        val airDate: String? = null,
        @field:Json(name = "episode_count")
        val episodeCount: Int? = null,
        @field:Json(name = "id")
        val id: Int? = null,
        @field:Json(name = "name")
        val name: String? = null,
        @field:Json(name = "overview")
        val overview: String? = null,
        @field:Json(name = "poster_path")
        val posterPath: String? = null,
        @field:Json(name = "season_number")
        val seasonNumber: Int? = null
    )

    data class SpokenLanguage(
        @field:Json(name = "english_name")
        val englishName: String? = null,
        @field:Json(name = "iso_639_1")
        val iso6391: String? = null,
        @field:Json(name = "name")
        val name: String? = null
    )
}