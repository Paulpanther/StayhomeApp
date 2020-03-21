package de.wvvh.stayhomeapp.ui.main.achievements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.IAchievement
import kotlinx.android.synthetic.main.achievement_card.view.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class AchievementAdapter(
    private val achievementList: List<IAchievement>,
    private val listener: OnAchievementClickedListener)
    : RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.achievement_card,
            parent,
            false)

        return AchievementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        val currentItem = achievementList[position]

        holder.icon.setImageResource(currentItem.imageResource)
        holder.title.setText(currentItem.titleResource)
        holder.desc.setText(currentItem.descriptionResource)
        holder.explanation.setText(currentItem.explanationResource)
        holder.bind(achievementList[position], listener);
    }

    override fun getItemCount() = achievementList.size

    class AchievementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.achievement_icon
        val title: TextView = itemView.achievement_title
        val desc: TextView = itemView.achievement_description
        val explanation: TextView = itemView.achievement_explanation

        fun bind(achievement: IAchievement, listener: OnAchievementClickedListener)
        {
            itemView.setOnClickListener {
                listener.onClick(achievement, this)
            }
        }
    }

    interface OnAchievementClickedListener {
        fun onClick(achievement: IAchievement, viewHolder: AchievementViewHolder)
    }
}