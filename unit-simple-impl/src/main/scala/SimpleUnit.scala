package com.cosmoloj.sc.unit.simple.impl

import com.cosmoloj.sc.unit.simple.api.{AbstractUnit, Factor, TransformedUnit}


abstract class SimpleUnit extends AbstractUnit {

  def shift(value: Double): TransformedUnit =
    SimpleTransformedUnit.of(SimpleUnitConverter.translation(value), this)

  def scaleMultiply(value: Double): TransformedUnit =
    SimpleTransformedUnit.of(SimpleUnitConverter.linear(value), this)

  def scaleDivide(value: Double): TransformedUnit = this.scaleMultiply(1.0 / value)

  def factor(numerator: Int, denominator: Int): Factor = SimpleFactor.of(this, numerator, denominator)

}
