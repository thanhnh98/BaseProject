/*
 * Created by Thanh Nguyen on 10/20/21, 4:42 PM
 */

package com.thanh_nguyen.baseproject.app.data.repository

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.app.data.database.StorageDatabase
import com.thanh_nguyen.baseproject.app.model.entities.StorageItemEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.internal.wait
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class LoginRepositoryImplTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    lateinit var db: StorageDatabase

    @Before
    fun before(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            StorageDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown(){
        db.close()
    }

    @Test
    fun testGetAllItems() = runBlocking {
        print("zô rồi")
        db.storageItemDao().getAllItems().collect {
            Log.e("THANH","get all items: ${Gson().toJson(it)}")
        }
    }

    @Test
    fun testAddItems(){
        val itemAdd = StorageItemEntity(
            2,
            "THANH NE ${System.currentTimeMillis()}",
            Random(100).nextInt().toString()
        )
        GlobalScope.launch {
            db.storageItemDao().insertItem(itemAdd)
            testGetAllItems()
        }
    }

    @Test
    fun testDeleteItems(){
        GlobalScope.launch {
            db.storageItemDao().deleteItem(1)
            testGetAllItems()
        }
    }
}