package xyz.v.edumap.activities

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import xyz.v.edumap.R
import xyz.v.edumap.adapters.LecturesAdapter
import xyz.v.edumap.databinding.ActivityLectureBinding
import xyz.v.edumap.objects.Lecture
import xyz.v.edumap.viewmodel.FirestoreViewmodel


class LectureActivity : AppCompatActivity() {
    lateinit var binding:ActivityLectureBinding
    lateinit var fvm:FirestoreViewmodel
    var adapter:LecturesAdapter? = null
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLectureBinding.inflate(layoutInflater)
        fvm = ViewModelProvider(this).get(FirestoreViewmodel::class.java)
        setContentView(binding.root)
        val al:ArrayList<Lecture> = ArrayList()
        val ref = Firebase.storage.reference
        ref.child("/videoplayback.mp4").downloadUrl.addOnSuccessListener {
            downloadManager(it.toString())
        }

        fvm.getLectures("18CHE21").observeForever {
            al.clear()
            for (doc in it){
                println("venu ${doc.name}")
                al.add(doc)
                adapter?.notifyDataSetChanged()
            }
        }

        adapter = LecturesAdapter(al)

        binding.backIv.setOnClickListener {
            onBackPressed()
        }

        binding.recyclerView.adapter = adapter


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
        overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_to_right)
    }

    private fun downloadManager(url: String) {
        val request = DownloadManager.Request(Uri.parse(url))
        request.setDescription("download")
        request.setTitle("")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        }
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "" + "lecture" + ".mp4"
        )
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }
}