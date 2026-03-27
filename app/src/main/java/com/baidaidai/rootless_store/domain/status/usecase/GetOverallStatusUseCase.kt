package com.baidaidai.rootless_store.data.status.repository

import javax.inject.Inject

class GetOverallStatusUseCase @Inject constructor(
    private val storeStatusRepositoryImpl: StoreStatusRepositoryImpl
) {
    operator fun invoke() = storeStatusRepositoryImpl.getOverallStatus()
}
