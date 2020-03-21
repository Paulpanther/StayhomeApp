package de.wvvh.stayhomeapp.ui.main.achievements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.ui.main.TabFragment

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
        achievementList.adapter = AchievementAdapter(AchievementStore.achievements)
        achievementList.layoutManager = GridLayoutManager(
            context,
            2,
            RecyclerView.VERTICAL,
            false)
        return root
    }
}