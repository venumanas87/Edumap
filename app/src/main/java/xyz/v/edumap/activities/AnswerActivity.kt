package xyz.v.edumap.activities

import android.os.Binder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import xyz.v.edumap.R
import xyz.v.edumap.adapters.AnswerAdapter
import xyz.v.edumap.databinding.ActivityAnswerBinding
import xyz.v.edumap.objects.Answer
import xyz.v.edumap.objects.CommunityAnswers
import xyz.v.edumap.objects.CommunityPost
import xyz.v.edumap.viewmodel.FirestoreViewmodel

class AnswerActivity : AppCompatActivity() {
    lateinit var binding:ActivityAnswerBinding
    var postObj:CommunityPost? = null
    lateinit var fvm:FirestoreViewmodel
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fvm = ViewModelProvider(this).get(FirestoreViewmodel::class.java)
        auth = Firebase.auth
        if (intent.extras != null){
            postObj = intent.extras!!.get("object") as CommunityPost
        }
        var namee:String? = "Neeraj Rajpoot"
        var role:String? = "Teacher"

        window.statusBarColor = ContextCompat.getColor(this,R.color.primaryBlue)

        binding.name.text = postObj?.name ?: ""
        binding.titleTv.text = postObj!!.title
        binding.subjectTv.text = postObj!!.subject
        binding.likesTv.text = postObj!!.likes.toString()
        binding.commentsTv.text = postObj!!.ansrList.size.toString()
        binding.seenTv.text = postObj!!.views.toString()

        binding.backIv.setOnClickListener{
            onBackPressed()
        }

        binding.sendBtn.setOnClickListener {
            if (auth.currentUser != null){
                namee = auth.currentUser!!.displayName.toString()
                role = "Student"
            }
            val ans = Answer(binding.answerEt.text.toString(),"0",namee,role)
            val ansList = ArrayList<CommunityAnswers>()
            ansList.add(CommunityAnswers(binding.answerEt.text.toString(),0,namee!!,role!!))
            fvm.postAnswer(postObj?.id.toString(),ans)
            setupAnswersCustom(ansList)
            binding.answerEt.setText("")
        }


        setupAnswers()

    }

    private fun setupAnswers() {
        val adapter = AnswerAdapter(postObj!!.ansrList)
        binding.rvAnswer.adapter = adapter
    }

    private fun setupAnswersCustom(ansrList:ArrayList<CommunityAnswers>) {
        val adapter = AnswerAdapter(ansrList)
        binding.rvAnswer.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
        overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_to_right)
    }
}