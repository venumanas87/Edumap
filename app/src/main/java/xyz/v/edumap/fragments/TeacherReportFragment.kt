package xyz.v.edumap.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import xyz.v.edumap.adapters.SubjecstAdapter
import xyz.v.edumap.databinding.FragmentTeacherHomeBinding
import xyz.v.edumap.databinding.FragmentTeacherReportBinding

class TeacherReportFragment : Fragment() {

    private var _binding: FragmentTeacherReportBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeacherReportBinding.inflate(inflater,container,false)

        return binding.root
    }

}