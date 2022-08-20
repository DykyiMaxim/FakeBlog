package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.models.Blog

@Database(entities = [Blog::class, BlogDBkey::class], version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverter::class,ListOfStringToStringTypeConverter::class)
abstract class BlogDB:RoomDatabase() {

companion object {
    fun getInstance(context: Context): BlogDB {
        return Room.databaseBuilder(context, BlogDB::class.java, "blogs").build()
        }
    }
    abstract fun getBlogDAO():BlogDao

}

