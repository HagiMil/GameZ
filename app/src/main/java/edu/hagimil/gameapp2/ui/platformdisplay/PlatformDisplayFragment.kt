package edu.hagimil.gameapp2.ui.platformdisplay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.hagimil.gameapp2.GamesApplication
import edu.hagimil.gameapp2.R
import edu.hagimil.gameapp2.adapters.GameAdapter
import edu.hagimil.gameapp2.databinding.FragmentPlatformdisplayBinding

class PlatformDisplayFragment : Fragment() {

    private lateinit var platformDisplayViewModel: PlatformDisplayViewModel
    private var _binding: FragmentPlatformdisplayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        platformDisplayViewModel = ViewModelProvider(this)[PlatformDisplayViewModel::class.java]
        _binding = FragmentPlatformdisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //gets argument from platform fragment
        val platform = arguments?.getString("platform") ?: return
        //send argument to view model
        platformDisplayViewModel.loadPlatformGames(platform)
        platformDisplayViewModel.platformGames.observe(viewLifecycleOwner) { game ->
            val adapter = GameAdapter(game) {
                //if internet connection viable shows game info on click
                if (GamesApplication.networkStatusChecker.hasInternet()) {
                    val bundle = Bundle()
                    bundle.putLong("id", it.gameId)
                    findNavController().navigate(
                        R.id.action_platformDisplayFragment_to_detailsFragment,
                        bundle
                    )
                } else {
                    //if no internet throws toast to let the user know
                    platformDisplayViewModel.error.observe(viewLifecycleOwner) {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}