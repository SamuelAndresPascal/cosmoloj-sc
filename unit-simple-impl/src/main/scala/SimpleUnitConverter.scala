package com.cosmoloj.sc.unit.simple.impl

import com.cosmoloj.sc.unit.simple.api.UnitConverter


class SimpleUnitConverter private (private val mScale: Double,
                                   private val mOffset: Double)
  extends UnitConverter {

  def scale: Double = mScale

  def offset: Double = mOffset

  override def inverse: UnitConverter = new SimpleUnitConverter(
    1.0 / mScale,
    -mOffset / mScale) {
    override def inverse: UnitConverter = SimpleUnitConverter.this
  }

  def linear: UnitConverter = {
    // comparaison stricte volontaire sur un flottant
    if (mOffset == 0.0) this
    else SimpleUnitConverter.linear(mScale)
  }

  def linearPow(pow: Double): UnitConverter = {
    // comparaison stricte volontaire sur des flottants
    if (mOffset == 0.0 && pow == 1.0)
    {
      this
    }
    else
    {
      SimpleUnitConverter.linear(Math.pow(mScale, pow))
    }
  }

  def convert(value: Double): Double = mScale * value + mOffset

  def concatenate(converter: UnitConverter): UnitConverter =
    SimpleUnitConverter.of(converter.scale * mScale, this.convert(converter.offset))
}

object SimpleUnitConverter {
  private val IDENTITY: UnitConverter = linear(1.0)

  def of(scale: Double, offset: Double): UnitConverter = new SimpleUnitConverter(scale, offset)

  def linear(scale: Double): UnitConverter = new SimpleUnitConverter(scale, 0.0)

  def translation(offset: Double): UnitConverter = new SimpleUnitConverter(1.0, offset)

  def identity: UnitConverter = IDENTITY
}
