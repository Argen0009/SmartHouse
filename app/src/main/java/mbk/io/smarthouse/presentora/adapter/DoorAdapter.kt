package mbk.io.smarthouse.presentora.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mbk.io.smarthouse.data.local.model.DoorEntity
import mbk.io.smarthouse.databinding.ItemCardCameraBinding
import mbk.io.smarthouse.databinding.ItemDoorBinding

class DoorAdapter(private val isDoor: Boolean) :
    ListAdapter<DoorEntity, DoorViewHolder>(DoorDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorViewHolder {
        return DoorViewHolder(
            ItemDoorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), isDoor
        )

    }

    override fun onBindViewHolder(holder: DoorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class DoorViewHolder(private var binding: ItemDoorBinding, private val isDoor: Boolean) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(door: DoorEntity) = with(binding) {
        doorIv.load(door.snapshot)
        doorNameTv.text = door.name
        doorNameTv.setOnClickListener {
            if (binding.doorCard.visibility == View.GONE) {
                slideOutViews(binding.doorCard)
            } else {
                slideInViews(binding.doorCard)
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
class DoorDiffUtil : DiffUtil.ItemCallback<DoorEntity>() {
    override fun areItemsTheSame(oldItem: DoorEntity, newItem: DoorEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DoorEntity, newItem: DoorEntity): Boolean {
        return oldItem == newItem
    }

}