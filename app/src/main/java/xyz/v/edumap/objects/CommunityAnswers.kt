package xyz.v.edumap.objects

import java.io.Serializable

data class CommunityAnswers(
    var answer:String,
    var likes:Int,
    var name:String,
    var role:String
):Serializable
