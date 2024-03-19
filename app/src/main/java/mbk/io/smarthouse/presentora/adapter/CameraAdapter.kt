package mbk.io.smarthouse.presentora.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mbk.io.smarthouse.data.local.model.camera.CameraEntity
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
            cameraTv.setOnClickListener {
                if (binding.videoIv.visibility == View.GONE) {
                    slideOutViews(binding.videoIv)
                } else {
                    slideInViews(binding.videoIv)
                }
            }
        }

    private fun slideOutViews(view: View) {
        val duration = 500L

        view.apply {
            visibility = View.VISIBLE
            translationY = -height.toFloat()

            animate()
                .translationY(0f)
                .setDuration(duration)
                .start()
        }
    }

    private fun slideInViews(view: View) {
        val duration = 500L

        view.apply {
            animate()
                .translationY(-height.toFloat())
                .setDuration(duration)
                .withEndAction {
                    visibility = View.GONE
                    translationY = 0f
                }
                .start()
        }
    }
}

class CameraDiffUtil : DiffUtil.ItemCallback<CameraEntity>() {
    override fun areItemsTheSame(oldItem: CameraEntity, newItem: CameraEntity) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: CameraEntity, newItem: CameraEntity) =
        oldItem == newItem

}