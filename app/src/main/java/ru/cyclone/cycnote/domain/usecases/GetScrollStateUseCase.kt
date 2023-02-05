package ru.cyclone.cycnote.domain.usecases

import ru.cyclone.cycnote.domain.repository.PreferencesStateRepository
import javax.inject.Inject

class GetScrollStateUseCase @Inject constructor(private val preferencesStateRepository: PreferencesStateRepository) {
    suspend operator fun invoke() = preferencesStateRepository.getScrollState()
}