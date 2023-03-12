package edu.hagimil.gameapp2.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import edu.hagimil.gameapp2.R
import edu.hagimil.gameapp2.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //gets id from the whatever game is being clicked on
        val id = arguments?.getLong("id") ?: return

        //sends the id to the view model who returns with the game info
        detailsViewModel.fetchGameDetails(id)
        detailsViewModel.gameDetails.observe(viewLifecycleOwner) { game ->
            binding.nameView.text = game.gameName
            binding.descView.text = game.description_raw
            Picasso.get().load(game.backgroundImage).into(binding.posterView)
        }

        detailsViewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}