package com.thanh_nguyen.baseproject.app.data.repository

import com.thanh_nguyen.baseproject.app.data.data_source.remote.LoginRemoteDataSource
import com.thanh_nguyen.baseproject.app.data.local_data.room_db.dao.StorageItemDao
import com.thanh_nguyen.baseproject.app.domain.repositories.LoginRepository
import com.thanh_nguyen.baseproject.app.model.entities.StorageItemEntity

class LoginRepositoryImpl(
    private val dataSource: LoginRemoteDataSource,
    private val storageDAO: StorageItemDao
    ): LoginRepository {
    override fun getAuthorInfo() = dataSource.getAuthorInfo()
    override fun getAllItems() = storageDAO.getAllItems()
    override suspend fun insertItem(item: StorageItemEntity) = storageDAO.insertItem(item)
}