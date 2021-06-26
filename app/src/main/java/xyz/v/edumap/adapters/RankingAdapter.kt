package xyz.v.edumap.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import xyz.v.edumap.R
import xyz.v.edumap.objects.Leaders

class RankingAdapter(val list:ArrayList<Leaders>):RecyclerView.Adapter<RankingAdapter.mvh>() {

    inner class mvh(view: View):RecyclerView.ViewHolder(view){
       val arrow:ImageView = view.findViewById(R.id.img_arr)
        val rankTV:TextView = view.findViewById(R.id.rankTv)
        val pointsTV:TextView = view.findViewById(R.id.pointsTv)
        val nameTV:TextView = view.findViewById(R.id.name_tv)
        val ll:LinearLayout = view.findViewById(R.id.ll)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mvh {
        val view = mvh(LayoutInflater.from(parent.context).inflate(R.layout.ranking_card,parent,false))
        return view
    }

    override fun onBindViewHolder(holder: mvh, position: Int) {
        val obj = list[position]
        holder.pointsTV.text = obj.points
        holder.nameTV.text = obj.name
        holder.rankTV.text = obj.rank

        if (position == 2) {
            holder.ll.visibility = View.GONE
            holder.arrow.visibility = View.VISIBLE
        } else {
            holder.ll.visibility = View.VISIBLE
            holder.arrow.visibility = View.GONE
        }

        if (position == 3) {
            holder.ll.setBackgroundColor(
                ContextCompat.getColor(
                    holder.arrow.context,
                    R.color.primaryBlue
                )
            )
            holder.pointsTV.setTextColor(
                ContextCompat.getColor(
                    holder.arrow.context,
                    R.color.light_blue_ygot
                )
            )
            holder.nameTV.setTextColor(Color.WHITE)
            holder.rankTV.setTextColor(Color.WHITE)
        } else {
            holder.ll.setBackgroundColor(
                ContextCompat.getColor(
                    holder.arrow.context,
                    android.R.color.transparent
                )
            )
            holder.pointsTV.setTextColor(ContextCompat.getColor(
                holder.arrow.context,
                R.color.dark_grey
            ))
            holder.nameTV.setTextColor(Color.BLACK)
            holder.rankTV.setTextColor(Color.BLACK)
        }

    }

    override fun getItemCount(): Int {
        return 5
    }

}