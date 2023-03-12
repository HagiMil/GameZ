package edu.hagimil.gameapp2.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//converting json to usable values
//used by retrofit and room
@Entity
@JsonClass(generateAdapter = true)
data class GameItem constructor(
    @Json(name = "id") @PrimaryKey val gameId: Long,
    @Json(name = "name") val gameName: String,
    @Json(name = "background_image") val backgroundImage: String,
    @Json(name = "released") val releaseDate: String
){
    //tells room to ignore
    @Ignore
    var metacritic: Int? = null
}