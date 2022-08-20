package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BlogDBkey (
    @PrimaryKey(autoGenerate = false)
    var id:String,
    var prev:Int?,
    var next:Int?
    )