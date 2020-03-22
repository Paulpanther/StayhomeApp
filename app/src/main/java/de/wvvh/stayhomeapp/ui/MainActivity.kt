package de.wvvh.stayhomeapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.achievements.ModuleLoader
import de.wvvh.stayhomeapp.achievements.Modules
import de.wvvh.stayhomeapp.ui.main.SectionsPagerAdapter
import de.wvvh.stayhomeapp.user.UserData
import de.wvvh.stayhomeapp.util.Storage
import de.wvvh.stayhomeapp.wifi.Connection
import de.wvvh.stayhomeapp.wifi.WifiHelper
import io.paperdb.Paper
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(
            this,
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        Paper.init(applicationContext)

        ModuleLoader.loadModules(Modules)
        WifiHelper.enqueueWorker()
        if (WifiHelper.isJustReturnedToHome()) {
            startActivity(Intent(this, NotHomeQuestionActivity::class.java))
        }

        AchievementStore.notifyAchievements()
    }
}