package xyz.v.edumap.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import xyz.v.edumap.activities.AddStudentActivity
import xyz.v.edumap.adapters.ClassesAdapter
import xyz.v.edumap.databinding.FragmentTeacherHomeBinding

class TeacherHomeFragment: Fragment() {

    private var _binding: FragmentTeacherHomeBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeacherHomeBinding.inflate(inflater,container,false)
        val classes:ArrayList<String> = ArrayList()
        classes.add("IX D")
        classes.add("IX B")
        classes.add("X A")
        classes.add("X F")
        val adapter = ClassesAdapter(classes)
        binding.recyclerViewSubjects.adapter = adapter
        binding.addBtn.setOnClickListener {
            startActivity(Intent(context,AddStudentActivity::class.java))
        }
        return binding.root
    }

}