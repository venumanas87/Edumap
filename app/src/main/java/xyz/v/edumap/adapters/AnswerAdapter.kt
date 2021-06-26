package xyz.v.edumap.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.v.edumap.R
import xyz.v.edumap.objects.CommunityAnswers

class AnswerAdapter(val ansrList:ArrayList<CommunityAnswers>):RecyclerView.Adapter<AnswerAdapter.mvh>() {

    inner class mvh(view: View):RecyclerView.ViewHolder(view){
        val answertv:TextView = view.findViewById(R.id.answer_tv)
        val nametv:TextView = view.findViewById(R.id.name_tv)
        val likestv:TextView = view.findViewById(R.id.likes_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mvh {
        val view = mvh(LayoutInflater.from(parent.context).inflate(R.layout.answer_card,parent,false))
        return view
    }

    override fun onBindViewHolder(holder: mvh, position: Int) {
      val obj = ansrList[position]
        holder.answertv.text = obj.answer
        holder.nametv.text = obj.name
        holder.likestv.text = obj.likes.toString()
    }

    override fun getItemCount(): Int = ansrList.size

}