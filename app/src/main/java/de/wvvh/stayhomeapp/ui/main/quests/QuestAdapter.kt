package de.wvvh.stayhomeapp.ui.main.quests

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.quests.IQuest
import kotlinx.android.synthetic.main.achievement_card.view.*
import kotlinx.android.synthetic.main.quest_card.view.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class QuestAdapter(
    private val questList: List<IQuest> )
    : RecyclerView.Adapter<QuestAdapter.QuestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.quest_card,
            parent,
            false)

        return QuestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestViewHolder, position: Int) {
        val currentItem = questList[position]

        holder.title.setText(currentItem.titleResource)
        holder.desc.setText(currentItem.descriptionResource)
        holder.exp.setText(R.string.exp)
    }

    override fun getItemCount() = questList.size

    class QuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.quest_title
        val desc: TextView = itemView.quest_description
        val exp: TextView = itemView.quest_exp
    }
}