package com.plcoding.cryptocurrencyappyt.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.plcoding.cryptocurrencyappyt.data.remote.dto.TeamMember
import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail

class Converters {

    @TypeConverter
    fun teamMemberListToJsonString(value: List<TeamMember>?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonStringToTeamMember(value: String) =
        Gson().fromJson(value, Array<TeamMember>::class.java).toList()

    @TypeConverter
    fun stringListToJsonString(value: List<String>?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonStringToStringList(value: String) =
        Gson().fromJson(value, Array<String>::class.java).toList()
}