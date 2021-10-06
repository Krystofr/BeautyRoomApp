package app.christopher.beautyroomapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.christopher.beautyroomapp.ui.dashboard.external.KristianaFragment
import app.christopher.beautyroomapp.ui.dashboard.external.SpecialistsFragment
import app.christopher.beautyroomapp.ui.dashboard.external.YasmineFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
           0->{
               KristianaFragment()
           }
            1->{
                SpecialistsFragment()
            }
            2->{
                YasmineFragment()
            }
            else ->{
                Fragment()
            }
        }
    }


}