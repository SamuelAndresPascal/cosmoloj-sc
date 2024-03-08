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
  
  def **(value: Int): AbstractUnit

  def *(value: Any): AbstractUnit

  def /(value: Any): AbstractUnit
  
  def unary_~ : AbstractUnit = this ** -1

  def +(value: Double): TransformedUnit = shift(value)

  def -(value: Double): TransformedUnit = shift(-value)

  def >>(unit: AbstractUnit): UnitConverter = getConverterTo(unit)

  def <<(unit: AbstractUnit): UnitConverter = unit.getConverterTo(this)
}
