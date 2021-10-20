/*
 * Created by Thanh Nguyen on 10/20/21, 4:14 PM
 */

package com.thanh_nguyen.baseproject.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.thanh_nguyen.baseproject.app.model.entities.StorageItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StorageItemDao {
    @Query("SELECT * FROM item")
    fun getAllItems(): Flow<List<StorageItemEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertItem(item: StorageItemEntity)

    @Query("delete from item where id = :id")
    suspend fun deleteItem(id: Int)

    @Query("delete from item")
    suspend fun deleteAllItems()
}