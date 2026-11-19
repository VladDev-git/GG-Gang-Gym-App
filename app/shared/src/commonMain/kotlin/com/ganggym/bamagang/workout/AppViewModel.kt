package com.ganggym.bamagang.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ganggym.bamagang.workout.data.seed.CatalogSeeder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(private val seeder: CatalogSeeder) : ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady: StateFlow<Boolean> = _isReady.asStateFlow()

    fun onLanguage(language: String) {
        viewModelScope.launch {
            seeder.seed(language)
            _isReady.value = true
        }
    }
}
