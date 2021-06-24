package xyz.v.edumap.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.R
import xyz.v.edumap.databinding.ActivityDashboardBinding
import xyz.v.edumap.databinding.FragmentHomeBinding
import xyz.v.edumap.fragments.CommunityFragment
import xyz.v.edumap.fragments.HomeFragment
import xyz.v.edumap.fragments.RakingFragment
import xyz.v.edumap.fragments.ReportFragment
import xyz.v.edumap.objects.StudentClass

class DashboardActivity : AppCompatActivity() {
    lateinit var binding:ActivityDashboardBinding
    lateinit var auth:FirebaseAuth
    var stdCls:StudentClass? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth



        val extras = intent.extras
        if (extras!=null){
         stdCls = extras.get("studClass") as StudentClass?
        }

        transactFragment(HomeFragment.newInstance(stdCls!!,""))

        binding.btmNavBar.setOnNavigationItemSelectedListener {
            println("venu ${it.itemId}")
            when(it.itemId){
                R.id.home->{
                    transactFragment(HomeFragment.newInstance(stdCls!!,""))

                }
                R.id.report->{
                    transactFragment(ReportFragment())
                    println("venu reportclick")

                }
                R.id.community->{
                    transactFragment(CommunityFragment())

                }
                R.id.ranking->{
                    transactFragment(RakingFragment())

                }
                R.id.profile->{
                    transactFragment(ReportFragment())
                }
            }
            true
        }

    }

    fun transactFragment(frag:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment,frag)
            commit()
        }
    }
}