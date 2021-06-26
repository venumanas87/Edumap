package xyz.v.edumap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.v.edumap.R
import xyz.v.edumap.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private var _binding:FragmentReportBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportBinding.inflate(inflater,container,false)
        binding.attProgLv.scale = 3f
        binding.attProgLv.setMinAndMaxProgress(0.0f,0.87f)
        return binding.root
    }

}