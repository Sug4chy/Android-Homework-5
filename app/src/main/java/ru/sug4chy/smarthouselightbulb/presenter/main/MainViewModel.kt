package ru.sug4chy.smarthouselightbulb.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sug4chy.smarthouselightbulb.data.model.Color
import ru.sug4chy.smarthouselightbulb.data.model.LightbulbState
import ru.sug4chy.smarthouselightbulb.domain.brightness.GetCurrentBrightnessUseCase
import ru.sug4chy.smarthouselightbulb.domain.brightness.SetBrightnessUseCase
import ru.sug4chy.smarthouselightbulb.domain.color.GetAllColorsUseCase
import ru.sug4chy.smarthouselightbulb.domain.color.GetCurrentColorUseCase
import ru.sug4chy.smarthouselightbulb.domain.color.SetColorUseCase
import ru.sug4chy.smarthouselightbulb.domain.state.GetCurrentStateUseCase
import ru.sug4chy.smarthouselightbulb.domain.state.SetStateUseCase
import ru.sug4chy.smarthouselightbulb.presenter.UiState
import ru.sug4chy.smarthouselightbulb.presenter.toUiState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCurrentStateUseCase: GetCurrentStateUseCase,
    private val setStateUseCase: SetStateUseCase,
    private val getCurrentBrightnessUseCase: GetCurrentBrightnessUseCase,
    private val setBrightnessUseCase: SetBrightnessUseCase,
    private val getAllColorsUseCase: GetAllColorsUseCase,
    private val getCurrentColorUseCase: GetCurrentColorUseCase,
    private val setColorUseCase: SetColorUseCase
) : ViewModel() {

    private val _currentState = MutableLiveData<UiState<LightbulbState>>(UiState.Loading)
    val currentState: LiveData<UiState<LightbulbState>>
        get() = _currentState

    private val _currentBrightness = MutableLiveData<UiState<Int>>(UiState.Loading)
    val currentBrightness: LiveData<UiState<Int>>
        get() = _currentBrightness

    private val _colors = MutableLiveData<UiState<List<String>>>(UiState.Loading)
    val colors: LiveData<UiState<List<String>>>
        get() = _colors

    private val _currentColor = MutableLiveData<UiState<Color>>(UiState.Loading)
    val currentColor: LiveData<UiState<Color>>
        get() = _currentColor

    fun turnOn() =
        setLightbulbState(LightbulbState.ON)

    fun turnOff() =
        setLightbulbState(LightbulbState.OFF)

    private fun setLightbulbState(state: LightbulbState) {
        viewModelScope.launch {
            setStateUseCase(state).fold(
                onSuccess = { _currentState.postValue(UiState.Success(state)) },
                onFailure = { _currentState.postValue(UiState.Failure(it.message ?: "")) }
            )

            if (state == LightbulbState.ON) {
                getCurrentBrightness()
                getCurrentColor()
            } else {
                _currentBrightness.postValue(UiState.Loading)
                _currentColor.postValue(UiState.Loading)
            }
        }
    }

    fun getCurrentState() {
        viewModelScope.launch {
            getCurrentStateUseCase().toUiState()
                .also { _currentState.postValue(it) }
        }
    }

    private fun getCurrentBrightness() {
        viewModelScope.launch {
            getCurrentBrightnessUseCase().toUiState()
                .also { _currentBrightness.postValue(it) }
        }
    }

    fun setBrightnessLevel(level: Int) {
        viewModelScope.launch {
            setBrightnessUseCase(level).fold(
                onSuccess = { _currentBrightness.postValue(UiState.Success(level)) },
                onFailure = { _currentBrightness.postValue(UiState.Failure(it.message ?: "")) }
            )
        }
    }

    fun getAllColors() {
        viewModelScope.launch {
            getAllColorsUseCase().toUiState()
                .also { _colors.postValue(it) }
        }
    }

    private fun getCurrentColor() {
        viewModelScope.launch {
            getCurrentColorUseCase().toUiState()
                .also { _currentColor.postValue(it) }
        }
    }

    fun setColor(color: String) {
        viewModelScope.launch {
            setColorUseCase(color).fold(
                onSuccess = { getCurrentColor() },
                onFailure = { _currentColor.postValue(UiState.Failure(it.message ?: "")) }
            )
        }
    }
}