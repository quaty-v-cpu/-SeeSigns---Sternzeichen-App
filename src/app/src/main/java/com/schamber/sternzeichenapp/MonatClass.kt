package com.schamber.sternzeichenapp

internal class MonatClass(val anfang: Int, val ende: Int, val name : String) {


    fun jahresTagBerechnen(gebursTag : Int) : Int {
        return gebursTag + anfang - 1 // Möglicher Fehler??
    }

    fun monatName() : String{
        return name
    }
}