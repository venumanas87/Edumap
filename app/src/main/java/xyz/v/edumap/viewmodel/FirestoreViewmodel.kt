package xyz.v.edumap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.firestore.PostModel
import xyz.v.edumap.objects.*

class FirestoreViewmodel:ViewModel() {
    val studentClassLD:MutableLiveData<StudentClass> = MutableLiveData()
    val postList:MutableLiveData<List<CommunityPost>> = MutableLiveData()
    val lectureList:MutableLiveData<List<Lecture>> = MutableLiveData()
    val studentList:MutableLiveData<List<Student>> = MutableLiveData()
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
                       val nm:String = doc.data.get("name") as String
                       subj.add(nm)
                   }
                   val studentClass = StudentClass(fname,count,subj)
                   studentClassLD.postValue(studentClass)

               }
            }
        return studentClassLD
    }

    fun getCommunity():LiveData<List<CommunityPost>>{
        var postListt:MutableList<CommunityPost> = mutableListOf()
        val postAl:ArrayList<CommunityPost> = ArrayList()
        basePath.collection("community")
            .get()
            .addOnSuccessListener {
                if (it != null){
                    for(doc in it){
                        val ansAl:ArrayList<CommunityAnswers> = ArrayList()
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
                                      //println("venu $ansr $name  $role  $likes")
                                    }
                                    val title = doc.get("postTitle").toString()
                                    val likes = doc.get("likes").toString().toInt()
                                    val subject = doc.get("subject").toString()
                                    val views = doc.get("views").toString().toInt()
                                    val name = doc.get("name").toString()
                                    val id = doc.id.toString()
                                     println("$title $likes $subject $views venu")
                                    val post = CommunityPost(id,title,likes,views,subject,name,ansAl)
                                    postAl.add(post)
                                    postListt.add(post)
                                    postList.postValue(postAl)
                                }
                            }
                            .addOnFailureListener { exception ->
                                println("venu Error getting documents: $exception")
                            }
                    }
                }
            }
    return postList
    }



    fun newPost(post:PostModel){
        basePath.collection("community")
            .add(post)
            .addOnSuccessListener {
                println("venu8 uploaded doc")
            }
            .addOnFailureListener {
                println("venu8 upload failed doc")
            }
    }

    fun getLectures(id:String):LiveData<List<Lecture>>{
        basePath.collection("subjects").document(id).collection("lectures")
            .get()
            .addOnSuccessListener {
                val ct = it?.toObjects(Lecture::class.java)
                for (doc in ct!!){
                }
                lectureList.postValue(ct!!)
            }
   return lectureList
    }

    fun postAnswer(id:String,ansr:Answer){
        basePath.collection("community").document(id).collection("answers")
            .add(ansr)
            .addOnSuccessListener {
                println("posted successfully")
            }
    }

    fun addStudents(stud:Student){
        basePath.collection("students")
            .add(stud)
            .addOnSuccessListener {
                println("venu posted successfully")
            }
    }

    fun getStudents():LiveData<List<Student>>{
        basePath.collection("students")
            .get()
            .addOnSuccessListener {
                if (it!=null){
                    val sl = it.toObjects(Student::class.java)
                    studentList.postValue(sl)
                }
            }
        return studentList
    }
}