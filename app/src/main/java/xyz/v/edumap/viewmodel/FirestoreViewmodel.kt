package xyz.v.edumap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.objects.CommunityAnswers
import xyz.v.edumap.objects.CommunityPost
import xyz.v.edumap.objects.StudentClass

class FirestoreViewmodel:ViewModel() {
    val studentClassLD:MutableLiveData<StudentClass> = MutableLiveData()
    val postList:MutableLiveData<ArrayList<CommunityPost>> = MutableLiveData()
    val db = Firebase.firestore
    val basePath = db.collection("class").document("3")
    val auth = Firebase.auth

    fun getStudentClass():LiveData<StudentClass>{

        val subj = ArrayList<String>()
        val fname = auth.currentUser?.displayName!!.split(" ")[0]
        var count = 0

        basePath.collection("subjects")
            .get()
            .addOnSuccessListener {
               if (it !=null){
                   for (doc in it){
                       count++
                       println("venu ${doc.id} ${doc.data} $count")
                       val nm:String = doc.data.get("name") as String
                       subj.add(nm)
                   }
                   val studentClass = StudentClass(fname,count,subj)
                   studentClassLD.postValue(studentClass)

               }
            }
        return studentClassLD
    }

    fun getCommunity():LiveData<ArrayList<CommunityPost>>{
        val postAl:ArrayList<CommunityPost> = ArrayList()
        basePath.collection("community")
            .get()
            .addOnSuccessListener {
                if (it != null){
                    for(doc in it){
                        var ansAl:ArrayList<CommunityAnswers> = ArrayList()
                        doc.reference.collection("answers")
                            .get()
                            .addOnSuccessListener {
                                if (it != null){
                                    for(inDoc in it){
                                        val ansr = inDoc.getString("answer").toString()
                                        val name  = inDoc.getString("name").toString()
                                        val role = inDoc.get("role").toString()
                                        val likes = inDoc.get("likes").toString().toInt()
                                        val comA = CommunityAnswers(ansr,likes, name, role)
                                        ansAl.add(comA)
                                        println("venu $ansr $name  $role  $likes")
                                    }
                                    val title = doc.get("postTitle").toString()
                                    val likes = doc.get("likes").toString().toInt()
                                    val subject = doc.get("subject").toString()
                                    val views = doc.get("views").toString().toInt()
                                    println("$title $likes $subject $views venu")
                                    val post = CommunityPost(title,likes,views,subject,ansAl)
                                    postAl.add(post)
                                }
                            }

                    }
                    postList.postValue(postAl)
                }
            }
    return postList
    }


    fun getCommunityLive(){
        val postAl:ArrayList<CommunityPost> = ArrayList()
        basePath
            .addSnapshotListener { snapshot, error ->
                if (snapshot != null&& snapshot.exists() ) {
                    println("Current data: ${snapshot.data}")
                    getCommunity()
                } else {

                }
            }
    }
}