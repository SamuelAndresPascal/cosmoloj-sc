package com.cosmoloj.sc.unit.simple.api

trait Factor {

  def dim: AbstractUnit

  def numerator: Int

  def denominator: Int

  def power: Double = if (denominator == 1) numerator.doubleValue() else numerator / denominator

  def *(value: Any): AbstractUnit
  
  def /(value: Any): AbstractUnit

}
