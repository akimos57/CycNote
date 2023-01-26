package ru.cyclone.cycnote.domain.usecases

import ru.cyclone.cycnote.domain.model.Note
import ru.cyclone.cycnote.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke()= noteRepository.getAllNotes()
}