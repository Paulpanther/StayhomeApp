package de.wvvh.stayhomeapp.ui.main.achievements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.ui.main.TabFragment
import kotlinx.android.synthetic.main.fragment_main_achievement.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class AchievementFragment: TabFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main_achievement, container, false)
        val achievementList = root.findViewById<RecyclerView>(R.id.achievement_view)
        val detailView = root.findViewById<View>(R.id.big_card)
        detailView.setOnClickListener { detailView.visibility = View.GONE }
        val clickListener = object: AchievementAdapter.OnAchievementClickedListener {
            override fun onClick(
                achievement: IAchievement,
                viewHolder: AchievementAdapter.AchievementViewHolder) {

                detailView.visibility = View.VISIBLE;

                val icon = detailView.findViewById<ImageView>(R.id.achievement_icon)
                val title = detailView.findViewById<TextView>(R.id.achievement_title)
                val desc = detailView.findViewById<TextView>(R.id.achievement_description)
                val explanation = detailView.findViewById<TextView>(R.id.achievement_explanation)

                icon.setImageResource(achievement.imageResource)
                title.setText(achievement.titleResource)
                desc.setText(achievement.descriptionResource)
                explanation.setText(achievement.explanationResource)
            }
        }
        achievementList.adapter = AchievementAdapter(AchievementStore.achievements, clickListener)
        achievementList.layoutManager = GridLayoutManager(
            context,
            2,
            RecyclerView.VERTICAL,
            false)
        return root
    }
}