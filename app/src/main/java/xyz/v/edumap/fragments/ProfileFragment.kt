package xyz.v.edumap.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.activities.SigninActivity
import xyz.v.edumap.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding:FragmentProfileBinding? = null

    private val binding get() = _binding!!
    private val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        binding.EditName.text = auth.currentUser?.displayName
        binding.EditEmail.text = auth.currentUser?.email


        binding.signoutTv.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context,SigninActivity::class.java))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}