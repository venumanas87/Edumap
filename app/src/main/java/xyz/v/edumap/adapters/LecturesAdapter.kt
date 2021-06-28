package xyz.v.edumap.adapters

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import xyz.v.edumap.R
import xyz.v.edumap.activities.LectureActivity
import xyz.v.edumap.objects.Lecture

class LecturesAdapter(val lectureList:ArrayList<Lecture>):RecyclerView.Adapter<LecturesAdapter.mvh>(){


    inner class mvh(view: View) : RecyclerView.ViewHolder(view){
        var titleTV  =view.findViewById<TextView>(R.id.title)
        var chapterTV  =view.findViewById<TextView>(R.id.chapter_tv)
        var dnloadIV = view.findViewById<ImageView>(R.id.dnload_iv)
        var mc: ImageView = view.findViewById(R.id.dnload_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mvh {
        val view =  mvh(LayoutInflater.from(parent.context).inflate(R.layout.lectures_crad,parent,false))
        return view
    }

    override fun onBindViewHolder(holder: mvh, position: Int) {

        val obj = lectureList[position]

        holder.titleTV.text = obj.name
        holder.chapterTV.text = "Chapter ${obj.chapter}"
        holder.dnloadIV.setOnClickListener {

        }


    }

    override fun getItemCount(): Int {
        return lectureList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}