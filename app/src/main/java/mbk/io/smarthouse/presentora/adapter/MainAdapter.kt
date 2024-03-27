package mbk.io.smarthouse.presentora.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import mbk.io.smarthouse.presentora.FavoriteFragment
import mbk.io.smarthouse.presentora.camera.CameraFragment
import mbk.io.smarthouse.presentora.door.DoorFragment

class MainAdapter(
    private val fragmentManager: FragmentManager,
    private val lifecycle: androidx.lifecycle.Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = listOf(
        CameraFragment(), DoorFragment()
    )

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CameraFragment()
            else -> DoorFragment()
        }
    }
}