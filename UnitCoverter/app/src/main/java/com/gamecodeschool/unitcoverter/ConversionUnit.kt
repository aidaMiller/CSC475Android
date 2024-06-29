package com.gamecodeschool.unitcoverter

enum class  ConversionUnit {
    FEET,
    METER,
    FAHRENHEIT,
    CELSIUS,
    POUND,
    KILOGRAM;

    companion object {
        fun getConversionUnitsFor(unit: ConversionUnit): Array<ConversionUnit> {
            when(unit) {
                FEET ->  return arrayOf(ConversionUnit.METER)
                METER ->  return arrayOf(ConversionUnit.FEET)
                FAHRENHEIT ->  return arrayOf(ConversionUnit.CELSIUS)
                CELSIUS ->  return arrayOf(ConversionUnit.FAHRENHEIT)
                POUND -> return arrayOf(ConversionUnit.KILOGRAM)
                KILOGRAM ->  return arrayOf(ConversionUnit.POUND)
            }
        }
        fun getLabelForUnit(unit: ConversionUnit): String {
            when(unit) {
                FEET -> return "ft"
                METER -> return "m"
                FAHRENHEIT ->  return "℉"
                CELSIUS ->  return "℃"
                POUND ->  return "lbs"
                KILOGRAM ->  return "kg"
            }
        }
    }

}