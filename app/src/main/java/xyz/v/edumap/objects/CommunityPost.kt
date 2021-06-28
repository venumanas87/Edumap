package xyz.v.edumap.objects

import java.io.Serializable

data class CommunityPost(
    var id:String/*? = "ok"*/,
    var title:String,
    var likes:Int,
    var views:Int,
    var subject:String,
    var name:String,
    var ansrList:ArrayList<CommunityAnswers>
):Serializable
