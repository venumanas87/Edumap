package xyz.v.edumap.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import xyz.v.edumap.adapters.PostAdapter
import xyz.v.edumap.databinding.CommunityTeachersFragmentBinding
import xyz.v.edumap.objects.CommunityPost
import xyz.v.edumap.viewmodel.FirestoreViewmodel


class CommunityTeachersFragment : Fragment() {
    private var _binding:CommunityTeachersFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var fvm: FirestoreViewmodel
    private var postal:ArrayList<CommunityPost> = ArrayList()
    var adapter:PostAdapter?= null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CommunityTeachersFragmentBinding.inflate(inflater,container,false)
        fvm = ViewModelProvider(activity as ViewModelStoreOwner).get(FirestoreViewmodel::class.java)

        this.adapter = PostAdapter(postal)
        binding.rvCommunity.adapter = this.adapter
        fvm.getCommunity().observeForever{
            println("venu observer ${it?.size}")
            postal.clear()
            if (it != null) {
                for (post in it) {
                    this.postal.add(post)
                }
                postal.sortWith(compareBy({ it.likes },{it.subject}))
            }
            adapter?.notifyDataSetChanged()
            binding.progress.visibility = View.GONE

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefresh.setOnRefreshListener {
            fvm.getCommunity()
            binding.swipeRefresh.isRefreshing = false
        }
    }
}