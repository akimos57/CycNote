package ru.cyclone.cycnote.presentation.screens.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.domain.model.ScrollState
import ru.cyclone.cycnote.domain.usecases.GetAllNoteUseCase
import ru.cyclone.cycnote.domain.usecases.GetScrollStateUseCase
import ru.cyclone.cycnote.domain.usecases.StoreScrollStateUseCase
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllNoteUseCase : GetAllNoteUseCase,
    private val storeScrollStateUseCase : StoreScrollStateUseCase,
    private val getScrollStateUseCase : GetScrollStateUseCase
    ): ViewModel() {
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>>
        get() = _notes

    var scrollPos = 0
        set(value) {
            storeScrollState(value)
            field = value
        }

    init {
        getAllNotes()
        getScrollState()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            getAllNoteUseCase.invoke().let {
                _notes.postValue(it)
            }
        }
    }

    private fun storeScrollState(state: Int) {
        viewModelScope.launch {
            storeScrollStateUseCase.invoke(ScrollState(state = state))
        }
    }

    private fun getScrollState() {
        viewModelScope.launch {
            getScrollStateUseCase.invoke()
        }
    }

    override fun onCleared() {
        storeScrollState(0)
        super.onCleared()
    }
}