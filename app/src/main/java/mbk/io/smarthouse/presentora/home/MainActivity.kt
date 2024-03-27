package mbk.io.smarthouse.presentora.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import mbk.io.smarthouse.presentora.adapter.MainAdapter
import mbk.io.smarthouse.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = MainAdapter(
            supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLiyayot, binding.viewPager) { tab, position ->
            tab.text = when (position)  {
                0 -> "Cameras"
                else -> {"Doors"}
            }
        }.attach()
    }
}