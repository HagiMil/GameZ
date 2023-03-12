package edu.hagimil.gameapp2.models

import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//converting json to usable values
//used by retrofit
@JsonClass(generateAdapter = true)
data class GameItemDetails constructor(
    @Json(name = "id") @PrimaryKey val gameId: Long,
    @Json(name = "name") val gameName: String,
    @Json(name = "background_image") val backgroundImage: String,
    val metacritic: Int?,
    val description: String,
    val description_raw: String
)