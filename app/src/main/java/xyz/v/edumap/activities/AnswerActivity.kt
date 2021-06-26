package xyz.v.edumap.activities

import android.os.Binder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xyz.v.edumap.R
import xyz.v.edumap.adapters.AnswerAdapter
import xyz.v.edumap.databinding.ActivityAnswerBinding
import xyz.v.edumap.objects.CommunityPost

class AnswerActivity : AppCompatActivity() {
    lateinit var binding:ActivityAnswerBinding
    var postObj:CommunityPost? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null){
            postObj = intent.extras!!.get("object") as CommunityPost
        }


        binding.name.text = postObj?.name ?: ""
        binding.titleTv.text = postObj!!.title
        binding.subjectTv.text = postObj!!.subject
        binding.likesTv.text = postObj!!.likes.toString()
        binding.commentsTv.text = postObj!!.ansrList.size.toString()
        binding.seenTv.text = postObj!!.views.toString()

        binding.backIv.setOnClickListener{
            onBackPressed()
        }


        setupAnswers()

    }

    private fun setupAnswers() {
        val adapter = AnswerAdapter(postObj!!.ansrList)
        binding.rvAnswer.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
        overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_to_right)
    }
}