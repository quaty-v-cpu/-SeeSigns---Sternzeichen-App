package com.schamber.sternzeichenapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.res.stringResource
import androidx.navigation.fragment.findNavController
import com.schamber.sternzeichenapp.databinding.FragmentForthBinding
import java.io.File
import kotlin.collections.mutableListOf


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ForthFragment : Fragment() {

    private var _binding: FragmentForthBinding? = null

    private val arr = Array<Int> (2) {0} // 0 - day1, 1 - month1
    private var OptionList : MutableList<String> = mutableListOf()
    private val LogikClass = SternzeichenLogikClass()

    private  var ResultList : MutableList<String> = mutableListOf<String>()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentForthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lesen der Daten aus der Datei, die ThirdFragment zubereitet hat
        val datei = File(context?.filesDir, "zweite_linie.txt")
        val datesStr = datei.readText()
        decodeDates(datesStr.toString()) // Dekodiert die datesStr in den arr von Dateszahlen


        // Button
        binding.BackChooseOptionsButton.setOnClickListener {
            findNavController().navigate(R.id.action_forthFragment_to_thirdFragment)
        }

        binding.HomeChooseOptionsButton.setOnClickListener {
            findNavController().navigate(R.id.action_forthFragment_to_firstFragment3)
        }

        binding.HelpActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_forthFragment_to_helpFragment)
        }


        // Optionen Liseners
        binding.Option1.setOnClickListener {
            if (binding.Option1.isChecked) {
                OptionList.add("respektiertwerden")
            }
            else {
                OptionList.remove("respektiertwerden")
            }
            // Update
            ResultList = LogikClass.getListOfSternzeichen(arr[0], arr[1], OptionList)
            ResultList = umwandleInWertenAusString(ResultList)
            binding.AusgabeTextEdit.setText(ResultList.joinToString())
        }

        binding.Option2.setOnClickListener {
            if (binding.Option2.isChecked) {
                OptionList.add("respektieren")
            }
            else {
                OptionList.remove("respektieren")
            }
            // Update
            ResultList = LogikClass.getListOfSternzeichen(arr[0], arr[1], OptionList)
            ResultList = umwandleInWertenAusString(ResultList)
            binding.AusgabeTextEdit.setText(ResultList.joinToString())
        }

        binding.Option3.setOnClickListener {
            if (binding.Option3.isChecked) {
                OptionList.add("verstandnisundgeborgenheit")
            }
            else {
                OptionList.remove("verstandnisundgeborgenheit")
            }
            // Update
            ResultList  = LogikClass.getListOfSternzeichen(arr[0], arr[1], OptionList)
            ResultList = umwandleInWertenAusString(ResultList)
            binding.AusgabeTextEdit.setText(ResultList.joinToString())
        }

        binding.Option4.setOnClickListener {
            if (binding.Option4.isChecked) {
                OptionList.add("streit")
            }
            else {
                OptionList.remove("streit")
            }
            // Update
            ResultList = LogikClass.getListOfSternzeichen(arr[0], arr[1], OptionList)
            ResultList = umwandleInWertenAusString(ResultList)
            binding.AusgabeTextEdit.setText(ResultList.joinToString())
        }

        binding.Option5.setOnClickListener {
            if (binding.Option5.isChecked) {
                OptionList.add("interessant")
            }
            else {
                OptionList.remove("interessant")
            }
            // Update
            ResultList = LogikClass.getListOfSternzeichen(arr[0], arr[1], OptionList)
            ResultList = umwandleInWertenAusString(ResultList)
            binding.AusgabeTextEdit.setText(ResultList.joinToString())
        }

        binding.Option6.setOnClickListener {
            if (binding.Option6.isChecked) {
                OptionList.add("abstandundfreiraum")
            }
            else {
                OptionList.remove("abstandundfreiraum")
            }
            // Update
            ResultList = LogikClass.getListOfSternzeichen(arr[0], arr[1], OptionList)
            ResultList = umwandleInWertenAusString(ResultList)
            binding.AusgabeTextEdit.setText(ResultList.joinToString())
        }

        binding.Option7.setOnClickListener {
            if (binding.Option7.isChecked) {
                OptionList.add("dominieren")
            }
            else {
                OptionList.remove("dominieren")
            }
            // Update
            ResultList = LogikClass.getListOfSternzeichen(arr[0], arr[1], OptionList)
            ResultList = umwandleInWertenAusString(ResultList)
            binding.AusgabeTextEdit.setText(ResultList.joinToString())
        }

        binding.Option8.setOnClickListener {
            if (binding.Option8.isChecked) {
                OptionList.add("dominiertwerden")
            }
            else {
                OptionList.remove("dominiertwerden")
            }
            // Update
            ResultList = LogikClass.getListOfSternzeichen(arr[0], arr[1], OptionList)
            ResultList = umwandleInWertenAusString(ResultList)
            binding.AusgabeTextEdit.setText(ResultList.joinToString())
        }

        binding.Option9.setOnClickListener {
            if (binding.Option9.isChecked) {
                OptionList.add("keinedominanz")
            }
            else {
                OptionList.remove("keinedominanz")
            }
            // Update
            ResultList = LogikClass.getListOfSternzeichen(arr[0], arr[1], OptionList)
            ResultList = umwandleInWertenAusString(ResultList)
            binding.AusgabeTextEdit.setText(ResultList.joinToString())
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

    private fun umwandleInWertenAusString(sternzeichenArr : MutableList<String>) : MutableList<String> {
        var resultArr = mutableListOf<String>()
        var str : String = ""
        var resultStr : String = ""
        for (i in 0..sternzeichenArr.count() - 1) {
            str = sternzeichenArr[i]
            resultStr = when (str) {
                "Steinbock" -> getString(R.string.Steinbock)
                "Wassermann" -> getString(R.string.Wassermann)
                "Fische" -> getString(R.string.Fische)
                "Widder" -> getString(R.string.Widder)
                "Stier" -> getString(R.string.Stier)
                "Zwillinge" -> getString(R.string.Zwillinge)
                "Krebs" -> getString(R.string.Krebs)
                "Loewe" -> getString(R.string.Loewe)
                "Jungfrau" -> getString(R.string.Jungfrau)
                "Waage" -> getString(R.string.Waage)
                "Skorpion" -> getString(R.string.Skorpion)
                "Schuetze" -> getString(R.string.Schuetze)

                else -> ""

            }
            resultArr.add(resultStr)
        }

        return resultArr
    }
}