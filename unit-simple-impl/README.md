# Simple Unit (implémentation Scala - implémentation de référence)

## Utilisation

Utilisation des unités transformées :

```scala
val m = new SimpleFundamentalUnit
val km = m.scaleMultiply(1000)
val cm = m.scaleDivide(100)
val cmToKm = cm.getConverterTo(km)

cmToKm.convert(3.0) // 0.00003
cmToKm.inverse.convert(0.00003) // 3
```

Utilisation des unités dérivées :

```scala        
val m = new SimpleFundamentalUnit
val km = m.scaleMultiply(1000)
val km2 = SimpleDerivedUnit.of(km.factor(2))
val cm = m.scaleDivide(100)
val cm2 = SimpleDerivedUnit.of(cm.factor(2))
val km2Tocm2 = km2.getConverterTo(cm2)

km2Tocm2.convert(3.0) // 30000000000
km2Tocm2.inverse.convert(30000000000) // 3
```

Utilisation des unités dérivées en combinant les dimensions :

```scala        
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

gPerM2ToTonPerKm2.convert(1.0) // 1
gPerM2ToTonPerKm2.inverse.convert(3.0) // 3
gPerM2ToTonPerCm2.convert(1.0) // 1e-4
gPerM2ToTonPerCm2.convert(3.0) // 3e-10
gPerM2ToTonPerCm2.offset() // 0.0
gPerM2ToTonPerCm2.scale() // 1e-10
gPerM2ToTonPerCm2.inverse.offset() // -0.0
gPerM2ToTonPerCm2.inverse.convert(3e-10) // 3
```

Utilisation des températures (conversions affines et linéaires) :

```scala        
val k = new SimpleFundamentalUnit
val c = k.shift(273.15)
val kToC = k.getConverterTo(c)

kToC.convert(0.0) // -273.15
kToC.inverse.convert(0.0) // 273.15

// en combinaison avec d'autres unités, les conversions d'unités de températures doivent devenir linéaires
val m = new SimpleFundamentalUnit
val cPerM = SimpleDerivedUnit.of(c, m.factor(-1))
val kPerM = SimpleDerivedUnit.of(k, m.factor(-1))
val kPerMToCPerM = kPerM.getConverterTo(cPerM)

kPerMToCPerM.convert(3.0) // 3
kPerMToCPerM.inverse.convert(3.0) // 3
```

Utilisation des conversions non décimales :

```scala        
val m = new SimpleFundamentalUnit
val km = m.scaleMultiply(1000.0)
val s = new SimpleFundamentalUnit
val h = s.scaleMultiply(3600.0)
val ms = SimpleDerivedUnit.of(m, s.factor(-1))
val kmh = SimpleDerivedUnit.of(km, h.factor(-1))
val msToKmh = ms.getConverterTo(kmh)

msToKmh.convert(100.0) // 360.
msToKmh.inverse.convert(18.0) // 5
```
