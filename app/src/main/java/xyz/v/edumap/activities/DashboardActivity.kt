package xyz.v.edumap.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.R
import xyz.v.edumap.databinding.ActivityDashboardBinding
import xyz.v.edumap.databinding.FragmentHomeBinding
import xyz.v.edumap.fragments.HomeFragment

class DashboardActivity : AppCompatActivity() {
    lateinit var binding:ActivityDashboardBinding
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_dashboard)
        auth = Firebase.auth
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment,HomeFragment.newInstance(auth.currentUser?.displayName!!,""))
            .commit()
    }
}