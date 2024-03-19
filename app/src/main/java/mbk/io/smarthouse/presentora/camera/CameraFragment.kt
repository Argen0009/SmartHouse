package mbk.io.smarthouse.presentora.camera

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mbk.io.smarthouse.data.local.model.camera.CameraEntity
import mbk.io.smarthouse.domain.base.BaseFragment
import mbk.io.smarthouse.databinding.FragmentCameraBinding
import mbk.io.smarthouse.presentora.adapter.CameraAdapter



@AndroidEntryPoint
class CameraFragment : BaseFragment() {
    private lateinit var binding: FragmentCameraBinding
    private val viewModel: CameraViewModel by viewModels()
    private val adapter = CameraAdapter(false)
    private var list: List<CameraEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
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
                target: RecyclerView.ViewHolder
            ): Boolean {
                // для перетаскивания элементов
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedCamera = adapter.currentList[position]

                lifecycleScope.launch {
                    viewModel.deleteCamera(deletedCamera)
                    val updatedList = adapter.currentList.toMutableList().apply {
                        removeAt(position)
                    }
                    adapter.submitList(updatedList)
                    Log.e("ololo", "onSwiped: $updatedList")
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvCamera)

        binding.rvCamera.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            list = viewModel.getDBCameras()
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
        viewModel.getCameras().stateHandler(
            success = { it ->
                val list = it.data.cameras
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.clearAll()
                    list.forEach {
                        val camera = CameraEntity(
                            favorites = it.favorites,
                            name = it.name,
                            rec = it.rec,
                            room = it.room,
                            snapshot = it.snapshot
                        )
                        viewModel.insertCamera(camera)
                    }
                    withContext(Dispatchers.Main) {
                        val listDB = viewModel.getDBCameras()
                        adapter.submitList(listDB)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}