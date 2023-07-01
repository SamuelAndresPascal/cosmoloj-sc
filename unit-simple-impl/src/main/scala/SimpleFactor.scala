package com.cosmoloj.sc.unit.simple.impl

import com.cosmoloj.sc.unit.simple.api.{AbstractUnit, Factor}

class SimpleFactor(private val unit: AbstractUnit,
                   private val num: Int,
                   private val den: Int)
  extends Factor {

  def dim: AbstractUnit = this.unit

  def numerator: Int = this.num

  def denominator: Int = this.den
}

object SimpleFactor {
  def of(dim: AbstractUnit, numerator: Int, denominator: Int = 1): Factor =
    new SimpleFactor(dim, numerator, denominator)
}
