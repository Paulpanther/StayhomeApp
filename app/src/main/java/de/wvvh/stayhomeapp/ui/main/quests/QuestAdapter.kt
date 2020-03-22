package de.wvvh.stayhomeapp.ui.main.quests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.quests.IQuest
import kotlinx.android.synthetic.main.level_card.view.*
import kotlinx.android.synthetic.main.quest_card.view.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class QuestAdapter(
    private val questList: List<IQuest>,
    private val userData: UserData)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            viewType,
            parent,
            false)

        return if(viewType == R.layout.level_card) LevelViewHolder(itemView)
            else QuestViewHolder(itemView)
    }

    override fun getItemViewType(position: Int) = when (position) {
        0 -> R.layout.level_card
        else -> R.layout.quest_card
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = questList[position]

        when(holder) {
            is QuestViewHolder -> {
                holder.title.setText(currentItem.titleResource)
                holder.desc.setText(currentItem.descriptionResource)
                holder.exp.text = currentItem.exp.toString() + " ERF"
                holder.finish.visibility = if(currentItem.userVerified) View.VISIBLE else View.GONE
            }
            is LevelViewHolder -> {
                holder.icon.setImageResource(userData.iconResource)
                holder.level.text = "Level " + userData.level.toString()
                holder.name.text = userData.name
            }
        }
    }

    override fun getItemCount() = questList.size

    class QuestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.quest_title
        val desc: TextView = itemView.quest_description
        val exp: TextView = itemView.quest_exp
        val skip: Button = itemView.quest_skip
        val finish: Button = itemView.quest_finish
    }

    class LevelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.user_icon
        val level: TextView = itemView.user_level
        val name: TextView = itemView.user_name
    }
}