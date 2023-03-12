package edu.hagimil.gameapp2.ui.platformdisplay

import android.app.Application
import androidx.lifecycle.*
import edu.hagimil.gameapp2.GamesApplication
import edu.hagimil.gameapp2.models.GameItem
import kotlinx.coroutines.launch

class PlatformDisplayViewModel(application: Application) : AndroidViewModel(application) {
    //expose the gameItem list live data as READ ONLY
    private val _platformGames: MutableLiveData<List<GameItem>> = MutableLiveData()
    val platformGames: LiveData<List<GameItem>> get() = _platformGames

    //expose the error live data as READ ONLY
    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    //if internet connection available fun uses api to retrieve game info using platform id
    //provided by the fragment
    //if can't shows game list saved in room database
    fun loadPlatformGames(platform: String) {
        viewModelScope.launch {
            if (GamesApplication.networkStatusChecker.hasInternet()) {
                _platformGames.postValue(GamesApplication.repository.getPlatformGames(platform))
            } else {
                _platformGames.postValue(GamesApplication.repository.refreshGames())
                _error.value = "No Internet Connection"
            }
        }
    }
}