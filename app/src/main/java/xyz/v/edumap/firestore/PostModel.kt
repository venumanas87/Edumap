package xyz.v.edumap.firestore

data class PostModel( var postTitle:String? = null,
                      var subject:String? =null,
                      var name:String? = null,
                      var likes:Int? = 0,
                      var views:Int? = 0,
                     )
