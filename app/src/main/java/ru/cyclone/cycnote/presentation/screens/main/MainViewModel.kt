package ru.cyclone.cycnote.presentation.screens.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.domain.usecases.GetAllNoteUseCase
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllNoteUseCase: GetAllNoteUseCase): ViewModel()
{
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>>
        get() = _notes

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            getAllNoteUseCase.invoke().let {
                _notes.postValue(it)
            }
        }
    }
}