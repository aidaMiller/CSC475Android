package com.gamecodeschool.unitcoverter

import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterTest {
    private val meter = ConversionUnit.METER
    private val feet = ConversionUnit.FEET
    private val fahrenheit = ConversionUnit.FAHRENHEIT
    private val celsius = ConversionUnit.CELSIUS
    private val pounds = ConversionUnit.POUND
    private val kilograms = ConversionUnit.KILOGRAM

    @Test
    fun feetToMeters() {
        val testValue = Converter.convert(5.0, feet, meter)
        assertEquals(1.524, testValue, 0.0)
    }

    @Test
    fun metersToFeet() {
        val testValue = Converter.convert(2.0, meter, feet)
        assertEquals(6.562, testValue, 0.001)
    }

    @Test
    fun fToC() {
        val testValue = Converter.convert(70.0, fahrenheit, celsius)
        assertEquals(21.111, testValue, 0.001)
    }

    @Test
    fun cToF() {
        val testValue = Converter.convert(20.0, celsius, fahrenheit)
        assertEquals(68.0, testValue, 0.001)
    }

    @Test
    fun poundsToKilos() {
        val testValue = Converter.convert(10.0, pounds, kilograms)
        assertEquals(4.535, testValue, 0.001)
    }

    @Test
    fun kilosToPounds() {
        val testValue = Converter.convert(15.0, kilograms, pounds)
        assertEquals(33.069, testValue, 0.001)
    }
}
