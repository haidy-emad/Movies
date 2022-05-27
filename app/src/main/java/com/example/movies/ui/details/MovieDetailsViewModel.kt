package com.example.movies.ui.details

import androidx.lifecycle.*
import com.example.movies.data.api.ApiHelper
import com.example.movies.data.repository.MoviesRepository
import com.example.movies.utils.MovieDetailsUiState
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val uiState: MutableLiveData<MovieDetailsUiState> = MutableLiveData()
    fun getUiState(): LiveData<MovieDetailsUiState> = uiState

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            try {
                val movieDetails = repository.getMovieDetails(id)
                uiState.postValue(MovieDetailsUiState.Success(movieDetails))
            } catch (exception: Exception) {
                uiState.postValue(MovieDetailsUiState.Error(exception))
            }
        }
    }
}

class MovieDetailsViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(MoviesRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}