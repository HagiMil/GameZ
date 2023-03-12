package edu.hagimil.gameapp2.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.hagimil.gameapp2.GamesApplication
import edu.hagimil.gameapp2.models.GameItemDetails
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    //expose the gameItemDetails live data as READ ONLY
    private val _gameDetails: MutableLiveData<GameItemDetails> = MutableLiveData()
    val gameDetails: LiveData<GameItemDetails> = _gameDetails

    //expose the error live data as READ ONLY
    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error


    //if internet connection available fun uses api to retrieve game info using id
    //provided by the fragment
    fun fetchGameDetails(id: Long) {
        viewModelScope.launch {
            if (GamesApplication.networkStatusChecker.hasInternet()) {
                _gameDetails.postValue(GamesApplication.rawgApi.getGameDetails(id))
            } else {
                _error.value = "No Internet Connection"
            }
        }
    }
}