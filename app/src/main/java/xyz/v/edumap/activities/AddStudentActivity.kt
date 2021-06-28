package xyz.v.edumap.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import xyz.v.edumap.databinding.ActivityAddStudentBinding
import xyz.v.edumap.objects.Student
import xyz.v.edumap.viewmodel.FirestoreViewmodel

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddStudentBinding
    lateinit var fvm:FirestoreViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        fvm = ViewModelProvider(this).get(FirestoreViewmodel::class.java)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            val name = binding.nameEt.text.toString()
            val email = binding.emailEt.text.toString()
            fvm.addStudents(Student(name,email))
            finish()
        }
    }
}