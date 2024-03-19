package mbk.io.smarthouse.presentora.door

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import mbk.io.smarthouse.presentora.adapter.DoorAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mbk.io.myhome.data.model.door.DoorEntity
import mbk.io.smarthouse.domain.base.BaseFragment
import mbk.io.smarthouse.databinding.FragmentDoorBinding

@AndroidEntryPoint
class DoorFragment : BaseFragment() {
    private lateinit var binding: FragmentDoorBinding

    private val adapter = DoorAdapter(true)

    private var list: List<DoorEntity> = mutableListOf()

    private val viewModel:DoorViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDoorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                // для перетаскивания элементов
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedDoor = adapter.currentList[position]

                lifecycleScope.launch {
                    viewModel.deleteDoor(deletedDoor)
                    val updatedList = adapter.currentList.toMutableList().apply {
                        removeAt(position)
                    }
                    adapter.submitList(updatedList)
                    Log.e("ololo", "onSwiped: $updatedList")
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvDoor)

        binding.rvDoor.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            list = viewModel.getDBDoors()
            withContext(Dispatchers.Main) {
                if (list.isEmpty()) {
                    getData()
                } else {
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        binding.swiperefresh.setOnRefreshListener {
            getData()
        }
    }

    fun getData() {
        viewModel.getDoors().stateHandler(
            success = { it ->
                val list = it.data
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.clearAll()
                    list.forEach {
                        val door = DoorEntity(
                            favorites = it.favorites,
                            name = it.name,
                            room = it.room,
                            snapshot = it.snapshot
                        )
                        viewModel.insert(door)

                    }
                    val listDB = viewModel.getDBDoors()
                    withContext(Dispatchers.Main) {
                        adapter.submitList(listDB)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}