package ru.cyclone.cycnote.domain.usecases

import ru.cyclone.cycnote.domain.repository.ScrollStateRepository
import javax.inject.Inject

class GetScrollStateUseCase @Inject constructor(private val scrollStateRepository: ScrollStateRepository) {
    suspend operator fun invoke() = scrollStateRepository.getScrollState()
}