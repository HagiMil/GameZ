package edu.hagimil.gameapp2.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.hagimil.gameapp2.models.GameItem

@Dao
interface GameDao {

    //add one game to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(gameItem: GameItem)

    //add a list of games to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGames(games: List<GameItem>)

    //retrieve all games in the database
    @Query("SELECT * FROM GameItem")
    fun getGames(): List<GameItem>

}