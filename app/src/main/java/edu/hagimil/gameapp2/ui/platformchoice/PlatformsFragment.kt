package edu.hagimil.gameapp2.ui.platformchoice

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.hagimil.gameapp2.R
import edu.hagimil.gameapp2.databinding.FragmentPlatformsBinding

class PlatformsFragment : Fragment() {


    private var _binding: FragmentPlatformsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlatformsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //every button sends the platform id's to the display platform
        //which will go the api and retrieve list using the id's

        binding.playStationImage.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("platform","187,18")
            goToDisplayFragment(bundle)
        }
        binding.xboxImage.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("platform","1,186")
            goToDisplayFragment(bundle)
        }
        binding.nintendoImage.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("platform","7")
            goToDisplayFragment(bundle)
        }
        binding.pcImage.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("platform","4")
            goToDisplayFragment(bundle)
        }
    }

    private fun goToDisplayFragment(bundle: Bundle) {
        findNavController().navigate(
            R.id.action_platformFragment_to_platformDisplayFragment,
            bundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}