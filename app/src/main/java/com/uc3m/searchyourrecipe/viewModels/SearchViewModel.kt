package com.uc3m.searchyourrecipe.viewModels

import androidx.lifecycle.*
import com.uc3m.searchyourrecipe.models.EdamamRecipe
import com.uc3m.searchyourrecipe.models.Recipe
import com.uc3m.searchyourrecipe.repository.EdamamRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(private  val repository: EdamamRepository): ViewModel() {

    val myResponse: MutableLiveData<Response<EdamamRecipe>> = MutableLiveData()
    val getResponse: MutableLiveData<Response<List<Recipe>>> = MutableLiveData()

    fun searchRecipe(query: String) {
        viewModelScope.launch {
            val response = repository.searchRecipe(query);
            myResponse.value = response
        }
    }

    fun getRecipe(uri: String) {
        viewModelScope.launch {
            val response = repository.getRecipe(uri);
            getResponse.value = response
        }
    }

}