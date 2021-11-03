/*
 * Created by Thanh Nguyen on 10/20/21, 4:42 PM
 */

package com.thanh_nguyen.baseproject.app.data.repository

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.thanh_nguyen.baseproject.app.data.local_data.room_db.StorageDatabase
import com.thanh_nguyen.baseproject.app.model.entities.StorageItemEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class LoginRepositoryImplTest{
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var db: StorageDatabase
    //private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @Before
    fun before(){
        //Dispatchers.setMain(mainThreadSurrogate)
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            StorageDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown(){
        db.close()
        //Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        //mainThreadSurrogate.close()
    }

    @Test
    fun testGetAllItems() = runBlockingTest {
        db.storageItemDao().getAllItems().collect {
            Log.e("THANH","get all items: ${Gson().toJson(it)}")
        }
        assert(true)
    }

    @Test
    fun testAddItems() = runBlockingTest {
        val itemAdd = StorageItemEntity(
            2,
            "THANH NE ${System.currentTimeMillis()}",
            Random(100).nextInt().toString()
        )
        async {
            db.storageItemDao().insertItem(itemAdd)
            testGetAllItems()
        }
        assert(true)
    }

    @Test
    fun testDeleteItems(){
        GlobalScope.launch {
            db.storageItemDao().deleteItem(1)
            testGetAllItems()
        }
    }
}