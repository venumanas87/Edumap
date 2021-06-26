package xyz.v.edumap.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.R
import xyz.v.edumap.databinding.ActivityDashboardBinding
import xyz.v.edumap.fragments.*
import xyz.v.edumap.fragments.CommunityFragment
import xyz.v.edumap.fragments.RakingFragment
import xyz.v.edumap.fragments.ReportFragment
import xyz.v.edumap.objects.StudentClass
import xyz.v.edumap.viewmodel.FirestoreViewmodel

class DashboardActivity : AppCompatActivity() {
    lateinit var binding:ActivityDashboardBinding
    lateinit var auth:FirebaseAuth
    var stdCls:StudentClass? = null
    private lateinit var fvm: FirestoreViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        fvm = ViewModelProvider(this).get(FirestoreViewmodel::class.java)

        configureSystemUI()
        getExtrasNow()


        transactFragment(HomeFragment.newInstance(stdCls!!,""))

        setUpNavigation()

    }

    private fun setUpNavigation() {
        binding.btmNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->{
                    transactFragment(HomeFragment.newInstance(stdCls!!,""))

                }
                R.id.report->{
                    transactFragment(ReportFragment())

                }
                R.id.community->{
                    transactFragment(CommunityFragment())

                }
                R.id.ranking->{
                    transactFragment(RakingFragment())

                }
                R.id.profile->{
                    transactFragment(ProfileFragment())
                }
            }
            true
        }
    }

    private fun getExtrasNow() {

        val extras = intent.extras
        if (extras!=null){
            stdCls = extras.get("studClass") as StudentClass?
        }
    }

    private fun configureSystemUI() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    fun transactFragment(frag:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment,frag)
            commit()
        }
    }
}