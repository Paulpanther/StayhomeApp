package de.wvvh.stayhomeapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import de.wvvh.stayhomeapp.BuildConfig
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.ui.main.achievements.AchievementFragment
import de.wvvh.stayhomeapp.ui.main.debug.ActionLogFragment
import de.wvvh.stayhomeapp.ui.main.quests.QuestFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_quests,
    R.string.tab_achievements,
    R.string.tab_debug_action_log
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, mode: Int) :
    FragmentPagerAdapter(fm, mode) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> QuestFragment()
        1 -> AchievementFragment()
        else -> ActionLogFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return if(BuildConfig.DEBUG) 3 else 2
    }
}