package edu.hagimil.gameapp2.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.hagimil.gameapp2.GamesApplication
import edu.hagimil.gameapp2.R
import edu.hagimil.gameapp2.adapters.GameAdapter
import edu.hagimil.gameapp2.api.RAWGApi
import edu.hagimil.gameapp2.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //gets a list of games
        homeViewModel.games.observe(viewLifecycleOwner) { game ->
            val adapter = GameAdapter(game) {
                //if no connection viable lets the user know
                //otherwise fills the recycler view
                if (GamesApplication.networkStatusChecker.hasInternet()) {
                    val bundle = Bundle()
                    bundle.putLong("id", it.gameId)
                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailsFragment,
                        bundle
                    )
                } else {
                    homeViewModel.error.observe(viewLifecycleOwner) {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        //shows progress bar depending on loading state
        homeViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressLoading.visibility = View.VISIBLE
            } else {
                binding.progressLoading.visibility = View.GONE
            }
        }

        //if no connection viable lets the user know
        homeViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}