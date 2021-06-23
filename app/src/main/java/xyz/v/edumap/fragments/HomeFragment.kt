package xyz.v.edumap.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.v.edumap.R
import xyz.v.edumap.adapters.SubjecstAdapter
import xyz.v.edumap.databinding.FragmentHomeBinding
import xyz.v.edumap.objects.StudentClass

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: StudentClass? = null
    private var _binding:FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as StudentClass?
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.classesTv.text = "${param1?.classesCount} Classes Today"
        binding.name.text = "Hello ${param1?.fname}"

        val adapter = SubjecstAdapter(param1!!.subjectsEnrolled)
        binding.recyclerViewSubjects.adapter = adapter



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: StudentClass, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}