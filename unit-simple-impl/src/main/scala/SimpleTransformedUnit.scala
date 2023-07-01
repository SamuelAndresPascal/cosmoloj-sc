package com.cosmoloj.sc.unit.simple.impl

import com.cosmoloj.sc.unit.simple.api.{AbstractUnit, TransformedUnit, UnitConverter}

class SimpleTransformedUnit(private val toRef: UnitConverter,
                            private val ref: AbstractUnit)
  extends SimpleUnit(), TransformedUnit {

  def reference: AbstractUnit = ref

  def toReference: UnitConverter = toRef
}

object SimpleTransformedUnit {
  def of(toReference: UnitConverter, reference: AbstractUnit): TransformedUnit =
    SimpleTransformedUnit(toReference, reference)

}
