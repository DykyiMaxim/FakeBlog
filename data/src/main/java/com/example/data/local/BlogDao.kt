package com.example.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.models.Blog

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogs(List:List<Blog>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogsKeys(l:List<BlogDBkey>)


    @Query("SELECT* FROM Blog")
    fun GetAllBlogsItems():PagingSource<Int,Blog>

    @Query("DELETE FROM Blog")
    suspend fun deleteAllItems()

    @Query("DELETE FROM BlogDBkey")
    suspend fun deleteAllBlogsKeys()


    @Query("SELECT * FROM BlogDBkey WHERE id=:id ")
    suspend fun getAllKeys(id:String):BlogDBkey


}