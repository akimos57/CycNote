package ru.cyclone.cycnote.domain.repository

import ru.cyclone.cycnote.data.local.dao.PreferencesRepositoryImpl
import ru.cyclone.cycnote.domain.model.ScrollState
import javax.inject.Inject

class PreferencesStateRepository @Inject constructor(private val preferencesRepositoryImpl: PreferencesRepositoryImpl) {
    suspend fun getScrollState() : List<ScrollState> = preferencesRepositoryImpl.getScrollState()

    suspend fun storeScrollState(state: ScrollState) = preferencesRepositoryImpl.storeScrollState(state = state)
}