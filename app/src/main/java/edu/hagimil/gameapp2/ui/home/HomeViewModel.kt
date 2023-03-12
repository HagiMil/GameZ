package edu.hagimil.gameapp2.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import edu.hagimil.gameapp2.GamesApplication
import edu.hagimil.gameapp2.models.GameItem
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    //expose the gameItem list live data as READ ONLY
    private val _games: MutableLiveData<List<GameItem>> = MutableLiveData()
    val games: LiveData<List<GameItem>> get() = _games
    //expose the error live data as READ ONLY
    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error
    //progress bar
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val  loading: LiveData<Boolean> = _loading


    //if internet connection available fun uses api to retrieve game list
    //if can't the repository will load room database
    init {
        viewModelScope.launch {
            if (GamesApplication.networkStatusChecker.hasInternet()){
                _loading.value = true
                _games.postValue(GamesApplication.repository.refreshGames())
                _loading.value = false
            } else{
                _games.postValue(GamesApplication.repository.refreshGames())
                _error.value = "No Internet Connection"
            }
        }
    }
}