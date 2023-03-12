package edu.hagimil.gameapp2.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import edu.hagimil.gameapp2.R
import edu.hagimil.gameapp2.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //search view, when submitted sends the string to the view model
        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.fetchGameDetails(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        //if no internet connection viable lets the user know
        searchViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        //retrieves game info using api thru the view model
        searchViewModel.gameDetails.observe(viewLifecycleOwner) { game ->
            binding.tvTitle.text = game.gameName
            Picasso.get().load(game.backgroundImage).into(binding.posterView)
            binding.posterView.visibility = View.VISIBLE
            binding.posterView.setOnClickListener {
                val bundle = Bundle()
                bundle.putLong("id", game.gameId)
                findNavController().navigate(R.id.action_searchFragment_to_detailsFragment, bundle)
            }
            binding.infoTv.visibility = View.VISIBLE

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}