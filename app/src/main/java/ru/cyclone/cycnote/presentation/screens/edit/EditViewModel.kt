package ru.cyclone.cycnote.presentation.screens.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.domain.usecases.AddNoteUseCase
import ru.cyclone.cycnote.domain.usecases.DeleteNoteUseCase
import ru.cyclone.cycnote.domain.usecases.GetNoteById
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val getNoteById: GetNoteById,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {
    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    fun getNoteById(id: Long) {
        viewModelScope.launch {
            getNoteById.invoke(id = id).let {
                _note.postValue(it)
            }
        }
    }

    fun deleteNote(
        onSuccess: () -> Unit = {},
        note: Note
    ) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(note = note)
            onSuccess()
        }
    }

    fun changeFavouriteState(
        onSuccess: () -> Unit = {},
        note : Note) : Boolean {
        viewModelScope.launch {
            note.isFavourite = !note.isFavourite
            addNoteUseCase.invoke(note)
            getNoteById(note.noteID)
            onSuccess()
        }
        return note.isFavourite
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch {
            addNoteUseCase.invoke(note = note)
            onSuccess()
        }
    }
}