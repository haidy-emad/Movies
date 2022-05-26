package com.example.movies

import androidx.lifecycle.*
import com.example.movies.data.api.ApiHelper
import com.example.movies.data.repository.MoviesRepository
import com.example.movies.utils.MoviesUiState
import kotlinx.coroutines.launch

// todo - use useCase
class MainViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val uiState: MutableLiveData<MoviesUiState> = MutableLiveData()
    fun getUiState(): LiveData<MoviesUiState> = uiState

    init {
        viewModelScope.launch {
            try {
                val movies = repository.getMovies()
                uiState.postValue(MoviesUiState.Success(movies.results))
            } catch (exception: Exception) {
                uiState.postValue(MoviesUiState.Error(exception))
            }
        }
    }
}

class MainViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MoviesRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}