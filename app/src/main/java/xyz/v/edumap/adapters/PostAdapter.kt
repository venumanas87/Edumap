package xyz.v.edumap.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import xyz.v.edumap.R
import xyz.v.edumap.activities.AnswerActivity
import xyz.v.edumap.objects.CommunityPost

class PostAdapter(val postList:ArrayList<CommunityPost>):RecyclerView.Adapter<PostAdapter.mvh>() {

    inner class mvh(view: View):RecyclerView.ViewHolder(view){
        val title:TextView = view.findViewById(R.id.title_tv)
        val subject:TextView = view.findViewById(R.id.subject_tv)
        val likes:TextView = view.findViewById(R.id.likes_tv)
        val seen:TextView = view.findViewById(R.id.seen_tv)
        val comments:TextView = view.findViewById(R.id.comments_tv)
        val mc:MaterialCardView = view.findViewById(R.id.mainCard)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mvh {
        val view =  mvh(LayoutInflater.from(parent.context).inflate(R.layout.post_card,parent,false))
        return view
    }

    override fun onBindViewHolder(holder: mvh, position: Int) {
        val obj = postList[position]
        holder.title.text = obj.title
        holder.subject.text = obj.subject
        holder.likes.text = obj.likes.toString()
        holder.comments.text = obj.ansrList.size.toString()
        holder.seen.text = obj.views.toString()
        val act = holder.comments.context as Activity

        holder.mc.setOnClickListener {
            it.context.startActivity(Intent(it.context,AnswerActivity::class.java).apply {
                putExtra("object",obj)
            })
            act.overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left)
        }
    }

    override fun getItemCount(): Int {
       return postList.size
    }
}