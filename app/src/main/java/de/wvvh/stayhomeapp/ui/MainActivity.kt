package de.wvvh.stayhomeapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.modules.ModuleLoader
import de.wvvh.stayhomeapp.modules.Modules
import de.wvvh.stayhomeapp.ui.main.SectionsPagerAdapter
import de.wvvh.stayhomeapp.wifi.WifiHelper
import io.paperdb.Paper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(applicationContext)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(
            this,
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)


        if (InitialLaunch.isFirstLaunch()) {
            startActivity(Intent(this, BoardingName::class.java))
        }


        ModuleLoader.loadModules(Modules)
        WifiHelper.enqueueWorker()
        if (WifiHelper.isJustReturnedToHome()) {
            startActivity(Intent(this, NotHomeQuestionActivity::class.java))
        }

        AchievementStore.notifyAchievements()
    }
}
