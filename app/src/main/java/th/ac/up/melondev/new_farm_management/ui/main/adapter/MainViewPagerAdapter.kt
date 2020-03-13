package th.ac.up.melondev.new_farm_management.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import th.ac.up.melondev.new_farm_management.ui.main.fragment.HistoryFragment
import th.ac.up.melondev.new_farm_management.ui.main.fragment.NotificationFragment
import th.ac.up.melondev.new_farm_management.ui.main.fragment.ProgramFragment

class MainViewPagerAdapter(private val fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProgramFragment()
            1 -> NotificationFragment()
            else -> HistoryFragment()
        }
    }
}