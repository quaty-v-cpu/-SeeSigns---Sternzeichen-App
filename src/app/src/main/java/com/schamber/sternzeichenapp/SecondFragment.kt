package com.schamber.sternzeichenapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.schamber.sternzeichenapp.databinding.FragmentSecondBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private var day1 : String = ""
    private var month1 : String = ""
    private var day2 : String = ""
    private var month2 : String = ""



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BackFloatingButtonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment2_to_firstFragment3)
        }

        binding.ForwardFloatingButtonSecond.setOnClickListener {
            if (checkEingaben() == true) {

                // Datesdaten an den BeschreibungFragment übergeben
                val datei = File(context?.filesDir, "erste_linie.txt")
                datei.writeText(day1 + " " + month1 + " " + day2 + " " + month2 + " ")

                // Zu BeschreibungFragment gehen
                findNavController().navigate(R.id.action_secondFragment2_to_beschreibungFragment2)
            }
        }

        binding.EingabeDate1.setOnClickListener {
            binding.EingabeDate1.setBackgroundResource(R.drawable.button_with_shadow_bg)
            binding.EingabeDate1.setText("")
        }

        binding.EingabeDate2.setOnClickListener {
            binding.EingabeDate2.setBackgroundResource(R.drawable.button_with_shadow_bg)
            binding.EingabeDate2.setText("")
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkEingaben(): Boolean {

        // Tag und Monat ausgeben in ein TextFeld
        val dateString1 = binding.EingabeDate1.text.toString()
        val dateString2 = binding.EingabeDate2.text.toString()
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
            binding.EingabeDate1.setBackgroundResource(R.drawable.button_with_shadow_warning)
            flage++
        }

        // Person 2
        val date2: Date? = try {
            inputFormat.parse(dateString2)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        if (date2 != null) {
            val dayFormat2 = SimpleDateFormat("dd", Locale.getDefault())
            val monthFormat2 = SimpleDateFormat("MM", Locale.getDefault())

            day2 = dayFormat2.format(date2)
            month2 = monthFormat2.format(date2)

        } else {
            binding.EingabeDate2.setBackgroundResource(R.drawable.button_with_shadow_warning)
            flage++
        }

        if (flage > 0) {
            return false
        } else {
            return true
        }
    }
}