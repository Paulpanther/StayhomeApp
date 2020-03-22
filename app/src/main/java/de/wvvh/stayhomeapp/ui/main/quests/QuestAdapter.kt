package de.wvvh.stayhomeapp.ui.main.quests

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.quests.IQuest
import de.wvvh.stayhomeapp.quests.QuestManager
import de.wvvh.stayhomeapp.user.UserData
import kotlinx.android.synthetic.main.level_card.view.*
import kotlinx.android.synthetic.main.quest_card.view.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class QuestAdapter(
    list: List<IQuest>,
    private val userData: UserData,
    private val context: Context?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val questList: List<IQuest> = listOf(EmptyQuest()) + list

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
                val expString = context?.getString(R.string.exp, currentItem.exp)
                holder.exp.text = expString// currentItem.exp
                holder.finish.visibility = if(currentItem.userVerified) View.VISIBLE else View.GONE
                holder.bind(currentItem, position, this)
            }
            is LevelViewHolder -> {
                holder.icon.setImageResource(userData.icon)
                val levelString = context?.getString(R.string.level, userData.level)
                holder.level.text = levelString // userData.level
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

        fun bind(quest: IQuest, position: Int, parent: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            skip.setOnClickListener {
                QuestManager.skipQuest(quest)
                parent.notifyItemRemoved(position)
            }
        }
    }

    class LevelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.user_icon
        val level: TextView = itemView.user_level
        val name: TextView = itemView.user_name
    }
}

class EmptyQuest: IQuest {
    override val tag: String = "debug.emptyQuest"
    override val exp = 0
    override val titleResource = 0
    override val descriptionResource = 0
    override val userVerified = false
    override var solved = false

    override fun check() { }
}