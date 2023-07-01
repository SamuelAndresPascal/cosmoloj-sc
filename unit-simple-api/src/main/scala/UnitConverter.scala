package com.cosmoloj.sc.unit.simple.api

trait UnitConverter {

  def scale: Double

  def offset: Double

  def inverse: UnitConverter

  def linear: UnitConverter

  def linearPow(pow: Double): UnitConverter

  def convert(value: Double): Double

  def concatenate(unitConverter: UnitConverter): UnitConverter
}
