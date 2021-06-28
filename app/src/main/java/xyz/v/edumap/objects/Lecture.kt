package xyz.v.edumap.objects

import java.io.Serializable

data class Lecture(
    var name:String? = null,
    var chapter:String?= null,
    var unit:String? = null
):Serializable
