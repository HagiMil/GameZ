package edu.hagimil.gameapp2.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.hagimil.gameapp2.GamesApplication
import edu.hagimil.gameapp2.models.GameItemDetails
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    //expose the gameItemDetails live data as READ ONLY
    private val _gameDetails: MutableLiveData<GameItemDetails> = MutableLiveData()
    val gameDetails: LiveData<GameItemDetails> get() = _gameDetails

    //expose the error live data as READ ONLY
    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    //if internet connection available fun uses api to retrieve game info using slug
    //provided by the fragment
    fun fetchGameDetails(slug: String) {
        viewModelScope.launch {
            if (GamesApplication.networkStatusChecker.hasInternet()) {
                try {
                    val response = GamesApplication.rawgApi.searchGame(slug.replace(' ', '-', true))
                    _gameDetails.postValue(response)
                } catch (e: Exception) {
                    _error.value = "Game not found"
                }
            } else {
                _error.value = "No internet connection"
            }
        }
    }
}