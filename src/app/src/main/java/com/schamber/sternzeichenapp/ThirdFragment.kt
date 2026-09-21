package com.schamber.sternzeichenapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.schamber.sternzeichenapp.databinding.FragmentThirdBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    private var day1: String = ""
    private var month1: String = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.BackFloatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_firstFragment3)
        }

        binding.ForwardFloatingButton.setOnClickListener {
            if (checkEingaben() == true) {

                // Datesdaten an den BeschreibungFragment übergeben
                val datei = File(context?.filesDir, "zweite_linie.txt")
                datei.writeText(day1 + " " + month1 + " ")

                findNavController().navigate(R.id.action_thirdFragment_to_forthFragment)
            }
        }

        binding.GeburtstagDate.setOnClickListener {
            binding.GeburtstagDate.setBackgroundResource(R.drawable.button_with_shadow_bg)
            binding.GeburtstagDate.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkEingaben(): Boolean {

        // Tag und Monat ausgeben in ein TextFeld
        val dateString1 = binding.GeburtstagDate.text.toString()

        var flage : Int = 0


        val inputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        // Bedeutet, dass die Überprüfung von Datum streng ist
        inputFormat.isLenient = false

        // Person 1
        val date1: Date? = try {
            inputFormat.parse(dateString1)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        if (date1 != null) {
            val dayFormat1 = SimpleDateFormat("dd", Locale.getDefault())
            val monthFormat1 = SimpleDateFormat("MM", Locale.getDefault())

            day1 = dayFormat1.format(date1)
            month1 = monthFormat1.format(date1)

        } else {
            binding.GeburtstagDate.setBackgroundResource(R.drawable.button_with_shadow_warning)
            flage++
        }


        if (flage > 0) {
            return false
        } else {
            return true
        }
    }
}