# Simple Unit (implémentation Scala - implémentation de référence)

* [Utilisation standard](#Utilisation-standard)
* [Utilisation avec surcharge des opérateurs](#Utilisation-avec-surcharge-des-opérateurs)

## Utilisation standard

L'utilisation standard se réfère aux méthodes implémentant la spécification Simple Unit.

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


## Utilisation avec surcharge des opérateurs

L'implémentation en Scala de Simple Unit utilise la surcharge d'opérateurs utilisable dans ce langage comme
alternative aux méthodes standards.

Utilisation des unités transformées :

```scala
val m = new SimpleFundamentalUnit
val km = m * 1000
val cm = m * 100
val cmToKm = cm >> km

cmToKm.convert(3.0) // 0.00003
(~cmToKm).convert(0.00003) // 3
```

Utilisation des unités dérivées :

```scala        
val m = new SimpleFundamentalUnit
val km = m * 1000
val km2 = km ** 2
val cm = m / 100
val cm2 = cm ** 2
val km2Tocm2 = km2 >> cm2

km2Tocm2.convert(3.0) // 30000000000
km2Tocm2.inverse.convert(30000000000) // 3
```

Utilisation des unités dérivées en combinant les dimensions :

```scala        
val m = new SimpleFundamentalUnit
val kg = new SimpleFundamentalUnit
val g = kg / 1000
val ton = kg * 1000
val gPerM2 = g / (m**2)
val km = m * 1000
val tonPerKm2 = ton * (~km**2)
val cm = m / 100
val tonPerCm2 = ton ~(cm**2)
val gPerM2ToTonPerKm2 = gPerM2 >> tonPerKm2
val gPerM2ToTonPerCm2 = tonPerCm2 << gPerM2

gPerM2ToTonPerKm2.convert(1.0) // 1
(~gPerM2ToTonPerKm2).convert(3.0) // 3
gPerM2ToTonPerCm2.convert(1.0) // 1e-4
gPerM2ToTonPerCm2.convert(3.0) // 3e-10
gPerM2ToTonPerCm2.offset() // 0.0
gPerM2ToTonPerCm2.scale() // 1e-10
(~gPerM2ToTonPerCm2).offset() // -0.0
(~gPerM2ToTonPerCm2).convert(3e-10) // 3
```

Utilisation des températures (conversions affines et linéaires) :

```scala        
val k = new SimpleFundamentalUnit
val c = k + 273.15
val kToC = k >> c

kToC.convert(0.0) // -273.15
(~kToC).convert(0.0) // 273.15

// en combinaison avec d'autres unités, les conversions d'unités de températures doivent devenir linéaires
val m = new SimpleFundamentalUnit
val cPerM = c / m
val kPerM = k / m
val kPerMToCPerM = kPerM >> cPerM

kPerMToCPerM.convert(3.0) // 3
(~kPerMToCPerM).convert(3.0) // 3
```

Utilisation des conversions non décimales :

```scala        
val m = new SimpleFundamentalUnit
val km = m * 1000.0
val s = new SimpleFundamentalUnit
val h = s * 3600.0
val ms = m / s
val kmh = km / h
val msToKmh = ms >> kmh

msToKmh.convert(100.0) // 360.
(~msToKmh).convert(18.0) // 5
```
