package com.schamber.sternzeichenapp

internal class SternzeichenClass(val anfang : Int, val ende: Int, val name : String) {
    fun entsprichtTagDemSternzeichen(JahresTag : Int): Boolean {


        if (JahresTag in anfang..ende) {
            return true
        }
        else {
            return false
        }
    }

    fun sternzeichenName() : String {
        return name
    }
}