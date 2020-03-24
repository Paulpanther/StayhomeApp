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
import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.quests.IQuest
import de.wvvh.stayhomeapp.quests.QuestManager
import de.wvvh.stayhomeapp.user.IUserDataUpdate
import kotlinx.android.synthetic.main.level_card.view.*
import kotlinx.android.synthetic.main.quest_card.view.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class QuestAdapter(
    private val questList: List<IQuest>,
    private val userDataStore: IUserDataUpdate,
    private val context: Context?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val numOfHeaders = 1

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
        when(holder) {
            is QuestViewHolder -> {
                val currentItem = questList[position - numOfHeaders]
                holder.title.setText(currentItem.titleResource)
                holder.desc.setText(currentItem.descriptionResource)
                val expString = context?.getString(R.string.exp, currentItem.exp)
                holder.exp.text = expString// currentItem.exp
                holder.finish.visibility = if(currentItem.userVerified) View.VISIBLE else View.GONE
                holder.bind(currentItem, this)
            }
            is LevelViewHolder -> {
                holder.icon.setImageResource(userDataStore.icon)
                val levelString = context?.getString(R.string.level, userDataStore.level)
                holder.level.text = levelString // userData.level
                holder.name.text = userDataStore.name
                holder.bind(userDataStore, this)
            }
        }
    }

    override fun getItemCount() = questList.size + numOfHeaders

    class QuestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.quest_title
        val desc: TextView = itemView.quest_description
        val exp: TextView = itemView.quest_exp
        val skip: Button = itemView.quest_skip
        val finish: Button = itemView.quest_finish

        fun bind(quest: IQuest, parent: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            skip.setOnClickListener {
                QuestManager.skipQuest(quest)
                parent.notifyItemRemoved(adapterPosition)
            }

            // TODO: button does not finish quest but only marks it as done. User needs to collect the exp by click
            finish.setOnClickListener {
                QuestManager.finishQuest(quest)
                parent.notifyItemRemoved(adapterPosition)
            }
        }
    }

    class LevelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.user_icon
        val level: TextView = itemView.user_level
        val name: TextView = itemView.user_name

        fun bind(userDataStore: IUserDataUpdate, parent: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            userDataStore.register { parent.notifyItemChanged(adapterPosition) }
        }
    }
}