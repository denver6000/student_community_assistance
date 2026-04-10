package xyz.denprog.studentcommunityassitance.user.feedback.add

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.denprog.studentcommunityassitance.MainActivityViewModel
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.databinding.FragmentSetFeedbackBinding

class SetFeedbackFragment : Fragment() {
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val viewModel: SetFeedbackViewModel by viewModels()
    private lateinit var binding: FragmentSetFeedbackBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    companion object {
        val RATINGS = listOf(1, 2, 3, 4, 5)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetFeedbackBinding.inflate(inflater, container, false)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            RATINGS.map {
                "$it Stars"
            }
        )

        binding.ratingSpinner.adapter = adapter
        mainActivityViewModel.selectedRatingIndex.observe(viewLifecycleOwner) { index ->
            if (index != null) {
                binding.ratingSpinner.setSelection(index)
            }
        }
        binding.ratingSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mainActivityViewModel.selectedRatingIndex.value = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        binding.setFeedBack.setOnClickListener {
            mainActivityViewModel.setFeedBackRequestId(
                binding.feedbackContent.text.toString(),
                onError = {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                },
                onSuccess =  {
                    viewLifecycleOwner.lifecycleScope.launch {
                        withContext(Dispatchers.Main) {
                            NavHostFragment.findNavController(requireParentFragment()).popBackStack()
                        }
                    }
                }
            )
        }

        return binding.root
    }
}