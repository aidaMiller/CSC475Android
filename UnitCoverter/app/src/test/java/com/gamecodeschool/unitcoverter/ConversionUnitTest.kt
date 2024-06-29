package com.gamecodeschool.unitcoverter

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class ConversionUnitTest {
    private val meter = ConversionUnit.METER
    private val feet = ConversionUnit.FEET
    private val fahrenheit = ConversionUnit.FAHRENHEIT
    private val celsius = ConversionUnit.CELSIUS
    private val pounds = ConversionUnit.POUND
    private val kilograms = ConversionUnit.KILOGRAM

    @Test
    fun feetToMeters() {
        assertArrayEquals(arrayOf(meter), ConversionUnit.getConversionUnitsFor(feet))
    }

    @Test
    fun metersToFeet() {
        assertArrayEquals(arrayOf(feet), ConversionUnit.getConversionUnitsFor(meter))
    }

    @Test
    fun fahrenheitToCelsius() {
        assertArrayEquals(arrayOf(celsius), ConversionUnit.getConversionUnitsFor(fahrenheit))
    }

    @Test
    fun celsiusToFahrenheit() {
        assertArrayEquals(arrayOf(fahrenheit), ConversionUnit.getConversionUnitsFor(celsius))
    }

    @Test
    fun poundsToKilograms() {
        assertArrayEquals(arrayOf(kilograms), ConversionUnit.getConversionUnitsFor(pounds))
    }

    @Test
    fun kilogramsToPounds() {
        assertArrayEquals(arrayOf(pounds), ConversionUnit.getConversionUnitsFor(kilograms))
    }

    @Test
    fun feetLabel() {
        assertEquals("ft", ConversionUnit.getLabelForUnit(feet))
    }

    @Test
    fun meterLabel() {
        assertEquals("m", ConversionUnit.getLabelForUnit(meter))
    }

    @Test
    fun fahrenheitLabel() {
        assertEquals("℉", ConversionUnit.getLabelForUnit(fahrenheit))
    }

    @Test
    fun celsiusLabel() {
        assertEquals("℃", ConversionUnit.getLabelForUnit(celsius))
    }

    @Test
    fun poundLabel() {
        assertEquals("lbs", ConversionUnit.getLabelForUnit(pounds))
    }

    @Test
    fun kilogramLabel() {
        assertEquals("kg", ConversionUnit.getLabelForUnit(kilograms))
    }
}
