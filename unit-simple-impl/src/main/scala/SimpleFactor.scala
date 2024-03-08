package com.cosmoloj.sc.unit.simple.impl

import com.cosmoloj.sc.unit.simple.api.{AbstractUnit, Factor}

class SimpleFactor(private val unit: AbstractUnit,
                   private val num: Int,
                   private val den: Int)
  extends Factor {

  def dim: AbstractUnit = this.unit

  def numerator: Int = this.num

  def denominator: Int = this.den

  def *(value: Any): AbstractUnit = SimpleDerivedUnit.of(this, value.asInstanceOf[Factor])
  
  def /(value: Any): AbstractUnit = SimpleDerivedUnit.of(this, SimpleFactor.of(value.asInstanceOf[Factor], -1))
}

object SimpleFactor {
  def of(dim: Factor, numerator: Int, denominator: Int = 1): Factor = {
    dim match
      case abstractUnit: AbstractUnit => SimpleFactor(abstractUnit, numerator, denominator)
      case _ => SimpleFactor(dim.dim, numerator * dim.numerator, denominator * dim.denominator)
  }
}
