package edu.hagimil.gameapp2

import android.app.Application
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import edu.hagimil.gameapp2.api.RAWGApi
import edu.hagimil.gameapp2.database.AppDatabase
import edu.hagimil.gameapp2.network.NetworkStatusChecker
import edu.hagimil.gameapp2.repository.GameRepository

//used to create singletons for the entire application
class GamesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: GamesApplication

        //create singleton room database
        private val db: AppDatabase by lazy {
            AppDatabase.create(instance)
        }

        //create singleton repository
        val repository: GameRepository by lazy {
            GameRepository(db.gameDao())
        }

        //create singleton api
        val rawgApi: RAWGApi by lazy {
            RAWGApi.create()
        }

        //create singleton network status checker
        val networkStatusChecker: NetworkStatusChecker by lazy {
            val connectivityManager = instance.getSystemService(ConnectivityManager::class.java)
            NetworkStatusChecker(connectivityManager)
        }

    }
}