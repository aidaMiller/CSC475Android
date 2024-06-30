package com.gamecodeschool.unitcoverter

enum class ConversionUnit(
    val label: String,
) {
    FEET("ft"),
    METER("m"),
    FAHRENHEIT("℉"),
    CELSIUS("℃"),
    POUND("lbs"),
    KILOGRAM("kg"),
    ;

    companion object {
        fun getConversionUnitsFor(unit: ConversionUnit): Array<ConversionUnit> =
            when (unit) {
                FEET -> arrayOf(METER)
                METER -> arrayOf(FEET)
                FAHRENHEIT -> arrayOf(CELSIUS)
                CELSIUS -> arrayOf(FAHRENHEIT)
                POUND -> arrayOf(KILOGRAM)
                KILOGRAM -> arrayOf(POUND)
            }

        fun getLabelForUnit(unit: ConversionUnit): String = unit.label

        fun from(findValue: String): ConversionUnit =
            ConversionUnit.entries.first {
                it.label == findValue
            }
    }
}
