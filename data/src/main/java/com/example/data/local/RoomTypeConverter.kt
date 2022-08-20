package com.example.data.local

import androidx.room.TypeConverter
import com.example.domain.models.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomTypeConverter {

    @TypeConverter
    fun ownerTOString(owner: Owner):String{
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun stringT0Owner(str:String):Owner{
        return Gson().fromJson(str,Owner::class.java)
    }

}

class ListOfStringToStringTypeConverter{
    @TypeConverter
    fun ListOfStringToString(str:List<String>):String{
        return Gson().toJson(str)
    }
    @TypeConverter
    fun strToListString(str:String):List<String>{
        return Gson().fromJson(str,object :TypeToken<List<String>>() {}.type)
    }

}