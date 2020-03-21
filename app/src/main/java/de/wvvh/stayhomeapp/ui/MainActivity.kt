package de.wvvh.stayhomeapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import de.wvvh.stayhomeapp.InitialLaunch
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementModules
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.ui.main.SectionsPagerAdapter
import de.wvvh.stayhomeapp.wifi.WifiHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(
            this,
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        startActivity(Intent(this, InitialLaunch::class.java))
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        loadModules()
    }

    private fun loadModules() = AchievementModules.modules.forEach { AchievementStore.loadModule(it) }
}