package ru.cyclone.cycnote.presentation.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.domain.usecases.DeleteNoteUseCase
import ru.cyclone.cycnote.domain.usecases.GetNoteById
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getNoteById: GetNoteById,
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

    fun deleteNote(onSuccess: () -> Unit) {
        viewModelScope.launch {
            note.value?.let {
                deleteNoteUseCase.invoke(note = it)
                onSuccess()
            }

        }
    }
}