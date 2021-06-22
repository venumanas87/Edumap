package xyz.v.edumap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.v.edumap.databinding.ActivitySigninBinding

lateinit var binding:ActivitySigninBinding
class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}