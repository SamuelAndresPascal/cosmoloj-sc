package com.cosmoloj.sc.unit.simple.impl

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions

class SimpleUnitTest {

    @Test def transformedUnitConversion(): Unit = {
        val m = new SimpleFundamentalUnit
        val km = m.scaleMultiply(1000)
        val cm = m.scaleDivide(100)
        val cmToKm = cm.getConverterTo(km)
        Assertions.assertEquals(0.00003, cmToKm.convert(3.0), 1e-10)
        Assertions.assertEquals(3.0, cmToKm.inverse.convert(0.00003), 1e-10)
    }

    @Test def derivedUnitConversion(): Unit = {
        val m = new SimpleFundamentalUnit
        val km = m.scaleMultiply(1000)
        val km2 = SimpleDerivedUnit.of(km.factor(2))
        val cm = m.scaleDivide(100)
        val cm2 = SimpleDerivedUnit.of(cm.factor(2))
        val km2Tocm2 = km2.getConverterTo(cm2)
        Assertions.assertEquals(30000000000.0, km2Tocm2.convert(3.0), 1e-10)
        Assertions.assertEquals(3.0, km2Tocm2.inverse.convert(30000000000.0), 1e-10)
    }

    @Test def combinedDimensionDerivedUnitConversion(): Unit = {
        val m = new SimpleFundamentalUnit
        val kg = new SimpleFundamentalUnit
        val g = kg.scaleDivide(1000)
        val ton = kg.scaleMultiply(1000)
        val gPerM2 = SimpleDerivedUnit.of(g, m.factor(-2))
        val km = m.scaleMultiply(1000)
        val tonPerKm2 = SimpleDerivedUnit.of(ton, km.factor(-2))
        val cm = m.scaleDivide(100)
        val tonPerCm2 = SimpleDerivedUnit.of(ton, cm.factor(-2))
        val gPerM2ToTonPerKm2 = gPerM2.getConverterTo(tonPerKm2)
        val gPerM2ToTonPerCm2 = gPerM2.getConverterTo(tonPerCm2)
        Assertions.assertEquals(1.0, gPerM2ToTonPerKm2.convert(1.0), 1e-10)
        Assertions.assertEquals(3.0, gPerM2ToTonPerKm2.inverse.convert(3.0), 1e-10)
        Assertions.assertEquals(1e-10, gPerM2ToTonPerCm2.convert(1.0), 1e-20)
        Assertions.assertEquals(3e-10, gPerM2ToTonPerCm2.convert(3.0), 1e-20)
        Assertions.assertEquals(0.0, gPerM2ToTonPerCm2.offset)
        Assertions.assertEquals(1e-10, gPerM2ToTonPerCm2.scale, 1e-10)
        Assertions.assertEquals(-0.0, gPerM2ToTonPerCm2.inverse.offset)
        Assertions.assertEquals(3.0, gPerM2ToTonPerCm2.inverse.convert(3e-10), 1e-10)
    }

    @Test def temperatures(): Unit = {
        val k = new SimpleFundamentalUnit
        val c = k.shift(273.15)
        val kToC = k.getConverterTo(c)
        Assertions.assertEquals(-273.15, kToC.convert(0), 1e-10)
        Assertions.assertEquals(273.15, kToC.inverse.convert(0), 1e-10)
        // en combinaison avec d'autres unités, les conversions d'unités de températures doivent devenir linéaires
        val m = new SimpleFundamentalUnit
        val cPerM = SimpleDerivedUnit.of(c, m.factor(-1))
        val kPerM = SimpleDerivedUnit.of(k, m.factor(-1))
        val kPerMToCPerM = kPerM.getConverterTo(cPerM)
        Assertions.assertEquals(4.0, kPerMToCPerM.convert(3.0), 1e-10)
        Assertions.assertEquals(3.0, kPerMToCPerM.inverse.convert(3.0), 1e-10)
    }

    @Test def speed(): Unit = {
        val m = new SimpleFundamentalUnit
        val km = m.scaleMultiply(1000.0)
        val s = new SimpleFundamentalUnit
        val h = s.scaleMultiply(3600.0)
        val ms = SimpleDerivedUnit.of(m, s.factor(-1))
        val kmh = SimpleDerivedUnit.of(km, h.factor(-1))
        val msToKmh = ms.getConverterTo(kmh)
        Assertions.assertEquals(360.0, msToKmh.convert(100.0), 1e-10)
        Assertions.assertEquals(5.0, msToKmh.inverse.convert(18.0), 1e-10)
    }
}
