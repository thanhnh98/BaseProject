package com.thanh_nguyen.baseproject.app.data.local_data.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thanh_nguyen.baseproject.app.data.local_data.room_db.dao.StorageItemDao
import com.thanh_nguyen.baseproject.app.model.entities.StorageItemEntity

@Database(version = 1, entities = [StorageItemEntity::class])
abstract class StorageDatabase: RoomDatabase() {

    abstract fun storageItemDao(): StorageItemDao
    companion object {
        private var instance: StorageDatabase? = null

        operator fun invoke(context: Context): StorageDatabase {
            return instance?: synchronized(Any()) {
                instance?:Room.databaseBuilder(
                    context,
                    StorageDatabase::class.java, "TestDB.db"
                ).build().also {
                    instance = it
                }
            }

        }
    }
}