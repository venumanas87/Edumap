package xyz.v.edumap.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.R
import xyz.v.edumap.databinding.ActivitySigninBinding
import xyz.v.edumap.viewmodel.FirestoreViewmodel

class SigninActivity : AppCompatActivity() {
     lateinit var binding:ActivitySigninBinding


     private lateinit var auth: FirebaseAuth
     private lateinit var fvm: FirestoreViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        fvm = ViewModelProvider(this).get(FirestoreViewmodel::class.java)
        auth = Firebase.auth
        setContentView(binding.root)
        setListeners()
        window.apply {
//            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  + View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
//            statusBarColor = Color.TRANSPARENT

        }
    }

     private fun setListeners() {
         binding.googleSigninBtn.setOnClickListener {
             signInToGoogle()
         }
     }


     fun signInToGoogle() {
         val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
             .requestIdToken(getString(R.string.default_web_client_id))
             .requestEmail()
             .build()

         val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this, gso)
         val signInIntent = googleSignInClient.signInIntent
         startActivityForResult(signInIntent, RC_SIGN_IN)
     }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if (requestCode == RC_SIGN_IN) {
             val task = GoogleSignIn.getSignedInAccountFromIntent(data)
             try {
                 val account = task.getResult(ApiException::class.java)!!
                 println("firebaseAuthWithGoogle:" + account.id)
                 firebaseAuthWithGoogle(account.idToken!!)
             } catch (e: ApiException) {
                 println("Google sign in failed $e")
             }
         }
     }

     private fun firebaseAuthWithGoogle(idToken: String) {
         val credential = GoogleAuthProvider.getCredential(idToken, null)
         auth.signInWithCredential(credential)
             .addOnCompleteListener(this) { task ->
                 if (task.isSuccessful) {
                     println("signInWithCredential:success")
                     val user = auth.currentUser
                     updateUI(user)
                 } else {
                     println( "signInWithCredential:failure" +  task.exception)
                     updateUI(null)
                 }
             }
     }

     fun updateUI(user: FirebaseUser?){
         if(user!=null){
             fvm.getStudentClass().observe(this, {
                 startActivity(Intent(this, DashboardActivity::class.java).apply {
                     putExtra("studClass",it)
                 })
                 overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left)
                 finish()
             })
         }else{
             startActivity(Intent(this, SigninActivity::class.java))
             overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
         }
     }

     companion object {
         private const val RC_SIGN_IN = 3001
     }

}