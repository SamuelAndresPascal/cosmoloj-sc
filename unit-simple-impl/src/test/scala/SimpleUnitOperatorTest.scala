package com.cosmoloj.sc.unit.simple.impl

import org.junit.jupiter.api.{Assertions, Test}

class SimpleUnitOperatorTest {

    @Test def transformedUnitConversion(): Unit = {
        val m = new SimpleFundamentalUnit
        val km = m * 1000
        val cm = m / 100
        val cmToKm = cm >> km
        Assertions.assertEquals(0.00003, cmToKm.convert(3.0), 1e-10)
        Assertions.assertEquals(3.0, (~cmToKm).convert(0.00003), 1e-10)
    }

    @Test def derivedUnitConversion(): Unit = {
        val m = new SimpleFundamentalUnit
        val km = m * 1000.0
        val km2 = km ** 2
        val cm = m / 100.0
        val cm2 = cm ** 2
        val km2Tocm2 = km2 >> cm2
        Assertions.assertEquals(30000000000.0, km2Tocm2.convert(3.0), 1e-10)
        Assertions.assertEquals(3.0, (~km2Tocm2).convert(30000000000.0), 1e-10)
    }

    @Test def combinedDimensionDerivedUnitConversion(): Unit = {
        val m = new SimpleFundamentalUnit
        val kg = new SimpleFundamentalUnit
        val g = kg / 1000
        val ton = kg * 1000
        val gPerM2 = g / (m**2)
        val km = m * 1000
        val tonPerKm2 = ton * (~km**2)
        val cm = m / 100
        val tonPerCm2 = ton * ~(cm**2)
        val gPerM2ToTonPerKm2 = gPerM2 >> tonPerKm2
        val gPerM2ToTonPerCm2 = tonPerCm2 << gPerM2
        Assertions.assertEquals(1.0, gPerM2ToTonPerKm2.convert(1.0), 1e-10)
        Assertions.assertEquals(3.0, (~gPerM2ToTonPerKm2).convert(3.0), 1e-10)
        Assertions.assertEquals(1e-10, gPerM2ToTonPerCm2.convert(1.0), 1e-20)
        Assertions.assertEquals(3e-10, gPerM2ToTonPerCm2.convert(3.0), 1e-20)
        Assertions.assertEquals(0.0, gPerM2ToTonPerCm2.offset)
        Assertions.assertEquals(1e-10, gPerM2ToTonPerCm2.scale, 1e-10)
        Assertions.assertEquals(-0.0, (~gPerM2ToTonPerCm2).offset)
        Assertions.assertEquals(3.0, (~gPerM2ToTonPerCm2).convert(3e-10), 1e-10)
    }

    @Test def temperatures(): Unit = {
        val k = new SimpleFundamentalUnit
        val c = k + 273.15
        val kToC = k >> c
        Assertions.assertEquals(-273.15, kToC.convert(0), 1e-10)
        Assertions.assertEquals(273.15, (~kToC).convert(0), 1e-10)
        // en combinaison avec d'autres unités, les conversions d'unités de températures doivent devenir linéaires
        val m = new SimpleFundamentalUnit
        val cPerM = c / m
        val kPerM = k / m
        val kPerMToCPerM = kPerM >> cPerM
        Assertions.assertEquals(3.0, kPerMToCPerM.convert(3.0), 1e-10)
        Assertions.assertEquals(3.0, (~kPerMToCPerM).convert(3.0), 1e-10)
    }

    @Test def speed(): Unit = {
        val m = new SimpleFundamentalUnit
        val km = m * 1000.0
        val s = new SimpleFundamentalUnit
        val h = s * 3600.0
        val ms = m / s
        val kmh = km / h
        val msToKmh = ms >> kmh
        Assertions.assertEquals(360.0, msToKmh.convert(100.0), 1e-10)
        Assertions.assertEquals(5.0, (~msToKmh).convert(18.0), 1e-10)
    }
}
