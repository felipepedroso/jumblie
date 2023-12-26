package br.pedroso.jumblie.startscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor() : ViewModel() {
    private val _theme: MutableStateFlow<String> = MutableStateFlow("Saints from UK countries")

    val theme: StateFlow<String> = _theme
}