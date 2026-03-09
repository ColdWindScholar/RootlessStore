package com.baidaidai.rootless_store.domain.status.usecase

import com.baidaidai.rootless_store.data.status.repository.StoreStatusRepositoryImpl
import com.baidaidai.rootless_store.domain.status.model.AndroidAndAPIStatus
import javax.inject.Inject

class GetAndroidAndAPIStatusUseCase @Inject constructor(
    private val storeStatusRepositoryImpl: StoreStatusRepositoryImpl
) {
    operator fun invoke(): AndroidAndAPIStatus = storeStatusRepositoryImpl.getAndroidAndAPIStatus()
}