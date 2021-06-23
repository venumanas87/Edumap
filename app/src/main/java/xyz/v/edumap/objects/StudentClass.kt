package xyz.v.edumap.objects

import java.io.Serializable

data class StudentClass(
    var fname:String,
    var classesCount: Int,
    var subjectsEnrolled:ArrayList<String>
    ):Serializable
