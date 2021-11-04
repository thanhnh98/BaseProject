package com.thanh_nguyen.baseproject.app.domain.usecases

import com.thanh_nguyen.baseproject.app.data.repository.LoginRepositoryDecorator
import com.thanh_nguyen.baseproject.app.domain.repositories.LoginRepository
import com.thanh_nguyen.baseproject.app.model.entities.StorageItemEntity

class LoginUseCase(
    private val loginRepository: LoginRepository
    ) {
    fun getAuthorInfo() = loginRepository.getAuthorInfo()
    fun getAllItems() = loginRepository.getAllItems()
    suspend fun insertItem(item: StorageItemEntity) = loginRepository.insertItem(item)
}