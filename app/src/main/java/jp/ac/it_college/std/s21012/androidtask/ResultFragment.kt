package jp.ac.it_college.std.s21012.androidtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import jp.ac.it_college.std.s21012.androidtask.databinding.FragmentResultBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultFragment : Fragment() {
    private var _binding: ResultFragment? = null
    private val binding get() = _binding!!
    private val args: ResultFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        _binding = FragmentResultBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(
                ResultDisplayFragmentDirections.actionResultDisplayFragmentToQuizSelectDisplayFragment()
            )
        }
        val score = args.score
        binding.textView4.text = getString(R.string.enpty, score)

        return binding.root
    }
}