package ru.cyclone.cycnote.domain.repository

import ru.cyclone.cycnote.data.local.dao.ScrollRepositoryImpl
import ru.cyclone.cycnote.domain.model.ScrollState
import javax.inject.Inject

class ScrollStateRepository @Inject constructor(private val scrollRepositoryImpl: ScrollRepositoryImpl) {
    suspend fun getScrollState() : List<ScrollState> = scrollRepositoryImpl.getScrollState()

    suspend fun storeScrollState(state: ScrollState) = scrollRepositoryImpl.storeScrollState(state = state)
}