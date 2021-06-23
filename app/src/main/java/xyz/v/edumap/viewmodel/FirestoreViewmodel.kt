package xyz.v.edumap.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.objects.StudentClass

class FirestoreViewmodel:ViewModel() {
    val studentClassLD:MutableLiveData<StudentClass> = MutableLiveData()
    val db = Firebase.firestore
    val auth = Firebase.auth

    fun getStudentClass():LiveData<StudentClass>{

        val subj = ArrayList<String>()
        val fname = auth.currentUser?.displayName!!.split(" ")[0]
        var count = 0

        db.collection("class").document("3").collection("subjects")
            .get()
            .addOnSuccessListener {
               if (it !=null){
                   for (doc in it){
                       count++
                       println("venu ${doc.data} $count")
                       val nm:String = doc.data.get("name") as String
                       subj.add(nm)
                   }
                   val studentClass = StudentClass(fname,count,subj)
                   studentClassLD.postValue(studentClass)

               }
            }
        return studentClassLD
    }
}