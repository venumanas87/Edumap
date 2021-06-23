package xyz.v.edumap.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.R
import xyz.v.edumap.viewmodel.FirestoreViewmodel

class MainActivity : AppCompatActivity() {


    private lateinit var auth:FirebaseAuth
    private lateinit var fvm:FirestoreViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        fvm = ViewModelProvider(this).get(FirestoreViewmodel::class.java)
        updateUI(auth.currentUser)
      /*  Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,SigninActivity::class.java))
            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
        },2000)*/
    }


    fun updateUI(user:FirebaseUser?){
        if(user!=null){
            fvm.getStudentClass().observe(this, Observer {
                startActivity(Intent(this, DashboardActivity::class.java).apply {
                    putExtra("studClass",it)
                })
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                finish()
            })
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this,SigninActivity::class.java))
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                finish()
            },2000)
        }
    }

}