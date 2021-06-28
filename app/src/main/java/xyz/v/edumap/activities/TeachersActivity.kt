package xyz.v.edumap.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import xyz.v.edumap.R
import xyz.v.edumap.databinding.ActivityTeachersBinding
import xyz.v.edumap.fragments.*

class TeachersActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTeachersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureSystemUI()

        transactFragment(TeacherHomeFragment())
        setUpNavigation()


    }

    private fun setUpNavigation() {
        binding.btmNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->{
                    transactFragment(TeacherHomeFragment())
                }
                R.id.report->{
                   transactFragment(TeacherReportFragment())

                }
                R.id.community->{
                    transactFragment(CommunityTeachersFragment())

                }
                R.id.profile->{
                //    transactFragment(ProfileFragment())
                }
            }
            true
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

    fun transactFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, frag)
            commit()
        }
    }
}