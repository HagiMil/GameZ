package edu.hagimil.gameapp2.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//converting json to usable values
//used by retrofit
@JsonClass(generateAdapter = true)
data class GameResponse(
    @Json(name = "results") val gameItems: List<GameItem>
)