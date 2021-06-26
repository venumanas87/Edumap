package xyz.v.edumap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.R
import xyz.v.edumap.adapters.RankingAdapter
import xyz.v.edumap.databinding.FragmentRakingBinding
import xyz.v.edumap.objects.Leaders


class RakingFragment : Fragment() {


    private var _binding:FragmentRakingBinding? = null

    val binding get() = _binding!!
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRakingBinding.inflate(inflater,container,false)
        auth = Firebase.auth

        val al:ArrayList<Leaders> = ArrayList()
        al.add(Leaders("Aditya Gupta","4","2105"))
        al.add(Leaders("Shahzeb Khan","5","2093"))
        al.add(Leaders("Aditya Gupta","6","2105"))
        al.add(Leaders(auth.currentUser?.displayName.toString(),"22","1568"))
        al.add(Leaders("Prince Kumar","23","1309"))

        val adapter = RankingAdapter(al)
        binding.rvRanking.adapter = adapter

        return binding.root
    }

}