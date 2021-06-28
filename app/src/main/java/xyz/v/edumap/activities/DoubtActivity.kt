package xyz.v.edumap.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.R
import xyz.v.edumap.databinding.ActivityDoubtBinding
import xyz.v.edumap.firestore.PostModel
import xyz.v.edumap.viewmodel.FirestoreViewmodel

class DoubtActivity : AppCompatActivity() {
    lateinit var binding:ActivityDoubtBinding
    lateinit var fvm:FirestoreViewmodel
    val user = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fvm =  ViewModelProvider(this).get(FirestoreViewmodel::class.java)
        binding = ActivityDoubtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            val title = binding.questionEt.text.toString()
            var name = "Neeraj Rajput"
            if (user.currentUser != null){
                name = user.currentUser!!.displayName.toString()
            }
            val subj = binding.subjectEt.text.toString()
            val post = PostModel(title,subj,name)
            fvm.newPost(post)
            Toast.makeText(this,"Question posted",Toast.LENGTH_SHORT).show()
            finish()
        }



    }
}