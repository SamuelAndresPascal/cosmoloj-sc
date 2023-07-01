package com.cosmoloj.sc.unit.simple.api

trait AbstractUnit extends Factor {

  def getConverterTo(target: AbstractUnit): UnitConverter = target.toBase.inverse.concatenate(toBase)

  def toBase: UnitConverter

  def dim: AbstractUnit = this

  def numerator = 1

  def denominator = 1

  def shift(value: Double): TransformedUnit

  def scaleMultiply(value: Double): TransformedUnit

  def scaleDivide(value: Double): TransformedUnit

  def factor(numerator: Int, denominator: Int = 1): Factor
}
