package xyz.v.edumap.objects

data class CommunityPost(
    var title:String,
    var likes:Int,
    var views:Int,
    var subject:String,
    var ansrList:ArrayList<CommunityAnswers>
)
