package ru.cyclone.cycnote.presentation.screens.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.domain.model.ScrollState
import ru.cyclone.cycnote.domain.usecases.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllNoteUseCase : GetAllNoteUseCase,
    private val storeScrollStateUseCase : StoreScrollStateUseCase,
    private val getScrollStateUseCase : GetScrollStateUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase
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
        updateAllNotes()
        getScrollState()
    }

    private fun updateAllNotes() {
        viewModelScope.launch {
            getAllNoteUseCase.invoke().let {
                _notes.postValue(it)
            }
        }
    }

    fun deleteNote(
        onSuccess: () -> Unit = {},
        note: Note
    ) {
        viewModelScope.launch {
            notes.value?.let {
                deleteNoteUseCase.invoke(note = note)
                updateAllNotes()
                onSuccess()
            }
        }
    }

    fun changeFavouriteState(
        onSuccess: () -> Unit = {},
        note : Note) {
        viewModelScope.launch {
            notes.value?.let {
                val n = Note(
                    id = note.id,
                    title = note.title.ifEmpty { "" },
                    content = note.content.ifEmpty { "" },
                    backgroundColor = note.backgroundColor,
                    isFavourite = !note.isFavourite
                )
                deleteNoteUseCase.invoke(note = note)
                addNoteUseCase.invoke(note = n)
                updateAllNotes()
                onSuccess()
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