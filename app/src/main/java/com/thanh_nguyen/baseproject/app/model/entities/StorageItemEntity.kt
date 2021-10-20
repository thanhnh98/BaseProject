package com.thanh_nguyen.baseproject.app.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thanh_nguyen.baseproject.app.model.BaseModel

@Entity(tableName = "item")
data class StorageItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "quantity")
    val quantity: String,
): BaseModel()