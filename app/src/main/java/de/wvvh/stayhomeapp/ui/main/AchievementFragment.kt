package de.wvvh.stayhomeapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.wvvh.stayhomeapp.R

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
        return root
    }
}