package xyz.denprog.studentcommunityassitance.user.requests_management.add_requests

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.databinding.FragmentAddRequestsBinding

@AndroidEntryPoint
class AddRequestsFragment : Fragment() {

    companion object {
        fun newInstance() = AddRequestsFragment()
    }

    private val viewModel: AddRequestsViewModel by viewModels()
    private lateinit var binding: FragmentAddRequestsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRequestsBinding.inflate(inflater, container, false)

        binding.submitRequest.setOnClickListener {
            viewModel.addRequest(
                title = binding.requestTitle.text.toString().trim(),
                category = binding.requestCategory.text.toString().trim(),
                description = binding.requestDescription.text.toString().trim(),
                onSuccess = {
                    NavHostFragment.findNavController(this).popBackStack()
                }
            )
        }

        return binding.root
    }
}
