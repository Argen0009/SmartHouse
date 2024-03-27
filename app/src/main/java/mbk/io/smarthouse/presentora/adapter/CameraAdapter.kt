package mbk.io.smarthouse.presentora.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mbk.io.smarthouse.data.local.model.CameraEntity
import mbk.io.smarthouse.databinding.ItemCardCameraBinding


class CameraAdapter(private val dataList: Boolean) :
    ListAdapter<CameraEntity, RecyclerViewHolder>(CameraDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder =
        RecyclerViewHolder(
            ItemCardCameraBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), dataList
        )

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class RecyclerViewHolder(
    private var binding: ItemCardCameraBinding,
    private val dataList: Boolean,
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(camera: CameraEntity) =
        with(binding) {
            videoIv.load(camera.snapshot)
            cameraTv.text = camera.name
        }
}

class CameraDiffUtil : DiffUtil.ItemCallback<CameraEntity>() {
    override fun areItemsTheSame(oldItem: CameraEntity, newItem: CameraEntity) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: CameraEntity, newItem: CameraEntity) =
        oldItem == newItem
}