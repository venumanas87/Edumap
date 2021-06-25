package xyz.v.edumap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import xyz.v.edumap.R
import xyz.v.edumap.activities.DashboardActivity
import xyz.v.edumap.adapters.PostAdapter
import xyz.v.edumap.databinding.FragmentCommunityBinding
import xyz.v.edumap.objects.CommunityPost
import xyz.v.edumap.viewmodel.FirestoreViewmodel





class CommunityFragment : Fragment() {
    private var _binding:FragmentCommunityBinding? = null
    private val binding get() = _binding!!
    private lateinit var fvm: FirestoreViewmodel
    private var postal:ArrayList<CommunityPost> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommunityBinding.inflate(inflater,container,false)
        fvm = ViewModelProvider(context as ViewModelStoreOwner).get(FirestoreViewmodel::class.java)
        val adapter = PostAdapter(postal)
        postal.clear()
        fvm.getCommunity().observe(this as LifecycleOwner, {
            for (post in it){
                postal.add(post)
            }
            adapter.notifyDataSetChanged()
        })
        binding.rvCommunity.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            fvm.getCommunity()
            binding.swipeRefresh.isRefreshing = false
        }
        return binding.root
    }

}