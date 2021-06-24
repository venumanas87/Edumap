package xyz.v.edumap.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import xyz.v.edumap.R
import java.util.*
import kotlin.collections.ArrayList

class SubjecstAdapter(val subjectList:ArrayList<String>):RecyclerView.Adapter<SubjecstAdapter.mvh>() {
    val colorList = arrayOf("#F45656","#E8D15B","#35DABC","#BB86FC","#FF4081","#FFAB40","#F45656","#E8D15B","#35DABC","#BB86FC","#FF4081","#FFAB40")

    inner class mvh(view:View) : RecyclerView.ViewHolder(view){
        var subTV  =view.findViewById<TextView>(R.id.subject)
        var mc:MaterialCardView = view.findViewById(R.id.mainCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mvh {
        val view =  mvh(LayoutInflater.from(parent.context).inflate(R.layout.subject_card,parent,false))
        view.setIsRecyclable(false)
        return view
    }

    override fun onBindViewHolder(holder: mvh, position: Int) {
        holder.subTV.text = subjectList.get(position)

        holder.mc.backgroundTintList = ColorStateList.valueOf(Color.parseColor(colorList.get(position)))
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}