package mbk.io.smarthouse.base
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import mbk.io.smarthouse.data.resoure.Resource


abstract class BaseFragment : Fragment() {

    fun <T> LiveData<Resource<T>>.stateHandler(
        success: (data: T) -> Unit,
        state: ((res: Resource<T>) -> Unit)? = null
    ) {
        observe(this@BaseFragment) { res ->
            state?.invoke(res)
            when (res) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), res.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (res.data != null) {
                        success(res.data)
                    }
                }

                else -> {
                    Toast.makeText(requireContext(), res.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}