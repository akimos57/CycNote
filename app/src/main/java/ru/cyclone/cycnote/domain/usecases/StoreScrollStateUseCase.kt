package ru.cyclone.cycnote.domain.usecases

import ru.cyclone.cycnote.domain.model.ScrollState
import ru.cyclone.cycnote.domain.repository.ScrollStateRepository
import javax.inject.Inject

class StoreScrollStateUseCase @Inject constructor(private val scrollStateRepository: ScrollStateRepository) {
    suspend operator fun invoke(state: ScrollState) = scrollStateRepository.storeScrollState(state = state)
}