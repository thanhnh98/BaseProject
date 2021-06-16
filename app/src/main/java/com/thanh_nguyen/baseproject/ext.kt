package com.thanh_nguyen.baseproject

import com.google.gson.Gson
import java.util.*

fun Any.toJson(): String{
    return Gson().toJson(this)
}