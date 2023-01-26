package ru.cyclone.cycnote.domain.usecases

import ru.cyclone.cycnote.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteById @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = noteRepository.getNoteById(id = id)
}