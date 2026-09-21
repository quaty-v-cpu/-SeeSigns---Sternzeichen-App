package com.schamber.sternzeichenapp

import java.time.Month
import java.time.MonthDay

internal class SternzeichenLogikClass {

    private val SternzeichenNamesArray = arrayOf(
        "Steinbock", "Wassermann", "Fische", "Widder", "Stier", "Zwillinge", "Krebs",
        "Loewe", "Jungfrau", "Waage", "Skorpion", "Schuetze"
    )

    private var MeinSternzeichen: String = "unknown"
    private var DeinSternzeichen: String = "unknown"

    private var SternzeichenTemp : String = ""

    // Für Options
    private val abstand_0 = mutableListOf("respektiertwerden", "respektieren", "verstandnisundgeborgenheit")
    private val abstand_1 = mutableListOf("respektiertwerden", "interessant", "dominieren")
    private val abstand_2 = mutableListOf("respektiertwerden", "respektieren", "interessant", "abstandundfreiraum", "keinedominanz")
    private val abstand_3 = mutableListOf("streit")
    private val abstand_4 = mutableListOf("respektieren", "verstandnisundgeborgenheit", "dominiertwerden", )
    private val abstand_5 = mutableListOf("streit")
    private val abstand_6 = mutableListOf("respektiertwerden", "respektieren", "interessant", "abstandundfreiraum", "keinedominanz")
    private val abstand_7 = mutableListOf("streit")
    private val abstand_8 = mutableListOf("respektiertwerden", "verstandnisundgeborgenheit", "dominieren")
    private val abstand_9 = mutableListOf("streit")
    private val abstand_10 = mutableListOf("respektieren", "interessant", "abstandundfreiraum", "keinedominanz", "respektiertwerden")
    private val abstand_11 = mutableListOf("respektieren", "interessant", "dominiertwerden")

    // Optionsfunctions

    fun getListOfSternzeichen(Tag : Int, Monat : Int, optionsList : MutableList<String>) : MutableList<String> {
        // Wir definieren früher, um wenn keine Optionen gewählt sind, die leere Liste zurückzugeben
        val SternzeichenArr = mutableListOf<String>()

        // Wenn optionsList leer ist, dann rausgehen
        if (optionsList.count() == 0) {
            return SternzeichenArr
        }

        // Sternzeichen aus dem Tag und Monat in SternzeichenTemp ermitteln
        SternzeichenTemp = getSternzeichen(Tag, Monat)

        // Ermittele eine Liste von Abständen, die zu optinenList passen
        val abstandArr = mutableListOf<Int>()

        if (abstand_0.containsAll(optionsList)) { abstandArr.add(0) }
        if (abstand_1.containsAll(optionsList)) { abstandArr.add(1) }
        if (abstand_2.containsAll(optionsList)) { abstandArr.add(2) }
        if (abstand_3.containsAll(optionsList)) { abstandArr.add(3) }
        if (abstand_4.containsAll(optionsList)) { abstandArr.add(4) }
        if (abstand_5.containsAll(optionsList)) { abstandArr.add(5) }
        if (abstand_6.containsAll(optionsList)) { abstandArr.add(6) }
        if (abstand_7.containsAll(optionsList)) { abstandArr.add(7) }
        if (abstand_8.containsAll(optionsList)) { abstandArr.add(8) }
        if (abstand_9.containsAll(optionsList)) { abstandArr.add(9) }
        if (abstand_10.containsAll(optionsList)) { abstandArr.add(10) }
        if (abstand_11.containsAll(optionsList)) { abstandArr.add(11) }

        // Ermittle die Sternzeichen, die den Abständen in  abstandArr entsprechen
        var str : String = ""
        for (i in 0..abstandArr.count() - 1) {
            str = berechneSternzeichenDurchAbstand(SternzeichenTemp, abstandArr[i])
            SternzeichenArr.add(str)
        }

        return SternzeichenArr
    }

    private fun berechneSternzeichenDurchAbstand(sternzeichen : String, abstand : Int) : String {

        var meinPos = SternzeichenNamesArray.indexOf(sternzeichen)
        var meinPosPlusAbstand = meinPos + abstand

        return if (meinPosPlusAbstand <= 11) SternzeichenNamesArray[meinPosPlusAbstand] else SternzeichenNamesArray[meinPosPlusAbstand - 12]
    }

    
    // -------------
    constructor() {

    }


    fun berechneAbstandVonSternzeichen(Tag1 : Int, Monat1 : Int, Tag2 : Int, Monat2 : Int) : Int {

        // Eingabeüberprüfung
        if (ueberpruefeEingabe(Tag1, Monat1) == false || ueberpruefeEingabe(Tag2, Monat2) == false){
            return -1
        }

        // Berechnung vom Abstand
        MeinSternzeichen = getSternzeichen(Tag1, Monat1)
        DeinSternzeichen = getSternzeichen(Tag2, Monat2)

        var meinPos = SternzeichenNamesArray.indexOf(MeinSternzeichen) + 1
        var deinPos = SternzeichenNamesArray.indexOf(DeinSternzeichen) + 1

        return if (meinPos > deinPos) 12 - (meinPos - deinPos) else deinPos - meinPos

    }



    private fun ueberpruefeEingabe(Tag: Int, Monat: Int): Boolean {
        when (Monat) {
            1 -> return if (Tag in 1..31) true else false // Januar
            2 -> return if (Tag in 1..29) true else false // Februar
            3 -> return if (Tag in 1..31) true else false // Maerz
            4 -> return if (Tag in 1..30) true else false // April
            5 -> return if (Tag in 1..31) true else false // Mai
            6 -> return if (Tag in 1..30) true else false // Juni
            7 -> return if (Tag in 1..31) true else false // Juli
            8 -> return if (Tag in 1..31) true else false // August
            9 -> return if (Tag in 1..30) true else false // September
            10 -> return if (Tag in 1..31) true else false // Oktober
            11 -> return if (Tag in 1..30) true else false // November
            12 -> return if (Tag in 1..31) true else false // Dezember
            else -> return false
        }
    }


    private fun getSternzeichen( tag: Int, monat: Int): String {
        val date = MonthDay.of(monat, tag)

        return when {
            date >= MonthDay.of(Month.MARCH, 21)     && date <= MonthDay.of(Month.APRIL, 20)     -> "Widder"
            date >= MonthDay.of(Month.APRIL, 21)     && date <= MonthDay.of(Month.MAY, 20)       -> "Stier"
            date >= MonthDay.of(Month.MAY, 21)       && date <= MonthDay.of(Month.JUNE, 21)      -> "Zwillinge"
            date >= MonthDay.of(Month.JUNE, 22)      && date <= MonthDay.of(Month.JULY, 22)      -> "Krebs"
            date >= MonthDay.of(Month.JULY, 23)      && date <= MonthDay.of(Month.AUGUST, 23)    -> "Loewe"
            date >= MonthDay.of(Month.AUGUST, 24)    && date <= MonthDay.of(Month.SEPTEMBER, 23) -> "Jungfrau"
            date >= MonthDay.of(Month.SEPTEMBER, 24) && date <= MonthDay.of(Month.OCTOBER, 23)   -> "Waage"
            date >= MonthDay.of(Month.OCTOBER, 24)   && date <= MonthDay.of(Month.NOVEMBER, 22)  -> "Skorpion"
            date >= MonthDay.of(Month.NOVEMBER, 23)  && date <= MonthDay.of(Month.DECEMBER, 21)  -> "Schuetze"
            // Steinbock überspringt den Jahreswechsel
            date >= MonthDay.of(Month.DECEMBER, 22)  || date <= MonthDay.of(Month.JANUARY, 20)   -> "Steinbock"
            date >= MonthDay.of(Month.JANUARY, 21)   && date <= MonthDay.of(Month.FEBRUARY, 19)  -> "Wassermann"
            else                                                                                 -> "Fische"
        }
    }
}


