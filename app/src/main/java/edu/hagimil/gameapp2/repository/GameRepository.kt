package edu.hagimil.gameapp2.repository

import edu.hagimil.gameapp2.api.RAWGApi
import edu.hagimil.gameapp2.database.dao.GameDao
import edu.hagimil.gameapp2.models.GameItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


//repository to connect between room and retrofit
class GameRepository(private val gameDao: GameDao) {

    //using coroutine, tries to get a list of popular games online, if cant, uses room database
    suspend fun refreshGames() : List<GameItem> {
        return withContext(Dispatchers.IO) {
            try {
                val games = RAWGApi.create().getPopularGames().gameItems
                gameDao.addGames(games)
                games
            } catch (e: Exception){
                gameDao.getGames()
            }
        }
    }

    //using coroutine, tries to get a list of games sorted by platform online, if cant, uses room database
    suspend fun getPlatformGames(platform: String): List<GameItem>{
        return withContext(Dispatchers.IO){
            try {
                val platformGames = RAWGApi.create().getPlatformGames(platform).gameItems
                gameDao.addGames(platformGames)
                platformGames
            }catch (e: Exception){
                gameDao.getGames()
            }
        }
    }

}