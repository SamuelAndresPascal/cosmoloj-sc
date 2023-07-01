package com.cosmoloj.sc.unit.simple.impl

import com.cosmoloj.sc.unit.simple.api.{AbstractUnit, DerivedUnit, Factor, UnitConverter}

class SimpleDerivedUnit(private val mDef: List[Factor]) extends SimpleUnit, DerivedUnit {

  def definition: List[Factor] = mDef

  def toBase: UnitConverter = {
    /*
    En combinaison avec d'autres unités, il ne faut plus appliquer de décalage d'origine d'échelle (température)
    mais uniquement appliquer le facteur d'échelle.
    */
    var transform: UnitConverter = SimpleUnitConverter.identity
    for (factor <- this.mDef) {
      transform = factor.dim.toBase.linearPow(factor.power).concatenate(transform)
    }
    transform
  }
}

object SimpleDerivedUnit {
  def of(definition: List[Factor]): DerivedUnit = SimpleDerivedUnit(definition)

  def of(definition: Factor*): DerivedUnit = of(definition.toList)
}
