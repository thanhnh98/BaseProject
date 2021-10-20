package com.thanh_nguyen.baseproject.app.data.data_source.local

import com.thanh_nguyen.baseproject.app.data.database.dao.StorageItemDao

class StorageItemDataSource(
    private val storageItemDao: StorageItemDao
) {
    fun getAllItems() = storageItemDao.getAllItems()
}