package com.schamber.sternzeichenapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.schamber.sternzeichenapp.databinding.BeschreibungLayoutBinding
import java.io.File


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BeschreibungFragment  : Fragment() {

    private var _binding: BeschreibungLayoutBinding? = null
    private val arr = Array<Int> (4) {0} // 0 - day1, 1 - month1, 2 - day2, 3 - month2

    private val LogikObject = SternzeichenLogikClass()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = BeschreibungLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Datesdaten bekommen und verarbeiten
        val datei = File(context?.filesDir, "erste_linie.txt")
        val datesStr = datei.readText()
        decodeDates(datesStr.toString()) // Dekodiert die datesStr in den arr von Dateszahlen

        // Den Abstand zwische den Sternzeichen berechnen
        val abstand = LogikObject.berechneAbstandVonSternzeichen(arr[0], arr[1], arr[2], arr[3])

        // Dem Abstand entsprechende Beschreibung anbinden
        binding.BeschreibungTextView.text = when (abstand) {
            0 -> getString(R.string.abstand_0)
            1 -> getString(R.string.abstand_1)
            2 -> getString(R.string.abstand_2)
            3 -> getString(R.string.abstand_3)
            4 -> getString(R.string.abstand_4)
            5 -> getString(R.string.abstand_5)
            6 -> getString(R.string.abstand_6)
            7 -> getString(R.string.abstand_7)
            8 -> getString(R.string.abstand_8)
            9 -> getString(R.string.abstand_9)
            10 -> getString(R.string.abstand_10)
            11 -> getString(R.string.abstand_11)
            else -> "Fehler"
        }




        binding.BackButton.setOnClickListener {
            findNavController().navigate(R.id.action_beschreibungFragment2_to_secondFragment2)
        }

        binding.HomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_beschreibungFragment2_to_firstFragment3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun decodeDates(sourceStr : String) {
        var arrIndex : Int = 0
        var Wert : String = ""
        var ch : String = ""
        for (i in sourceStr) {
            ch = i.toString()
            if (ch != " ")  {
                Wert += ch
            }
            else {
                arr[arrIndex] = Wert.toInt()
                arrIndex += 1
                Wert = ""
            }
        }
    }

}