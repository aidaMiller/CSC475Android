package com.gamecodeschool.unitcoverter

class Converter {
    companion object {
        fun convert(
            value: Double,
            fromUnit: ConversionUnit,
            toUnit: ConversionUnit,
        ): Double =
            when (fromUnit) {
                ConversionUnit.FEET ->
                    if (toUnit == ConversionUnit.METER) value * 0.3048 else 0.0

                ConversionUnit.METER ->
                    if (toUnit == ConversionUnit.FEET) value / 0.3048 else 0.0

                ConversionUnit.FAHRENHEIT ->
                    if (toUnit == ConversionUnit.CELSIUS) (value - 32) * 5 / 9 else 0.0

                ConversionUnit.CELSIUS ->
                    if (toUnit == ConversionUnit.FAHRENHEIT) (value * 9 / 5) + 32 else 0.0

                ConversionUnit.POUND ->
                    if (toUnit == ConversionUnit.KILOGRAM) value / 2.205 else 0.0

                ConversionUnit.KILOGRAM ->
                    if (toUnit == ConversionUnit.POUND) value / 0.4536 else 0.0
            }
    }
}
