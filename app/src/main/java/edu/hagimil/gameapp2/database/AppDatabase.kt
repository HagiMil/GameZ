package edu.hagimil.gameapp2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.hagimil.gameapp2.database.dao.GameDao
import edu.hagimil.gameapp2.models.GameItem

const val DB_NAME = "AppDatabase"
const val DB_VERSION = 2

//basic room database with 1 entity, uses 1 dao
@Database(entities = [GameItem::class], version = DB_VERSION)
abstract class AppDatabase:RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object{
        fun create(context: Context):AppDatabase =
            Room
                .databaseBuilder(context,AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}