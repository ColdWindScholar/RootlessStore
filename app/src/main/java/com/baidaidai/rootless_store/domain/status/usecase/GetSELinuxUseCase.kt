package com.baidaidai.rootless_store.domain.status.usecase

import com.baidaidai.rootless_store.data.status.repository.StoreStatusRepositoryImpl
import com.baidaidai.rootless_store.domain.status.model.SELinuxStatus
import javax.inject.Inject

class GetSELinuxUseCase @Inject constructor(
    private val storeStatusRepositoryImpl: StoreStatusRepositoryImpl
) {
    operator fun invoke(): SELinuxStatus = storeStatusRepositoryImpl.getSELinuxStatus()
}