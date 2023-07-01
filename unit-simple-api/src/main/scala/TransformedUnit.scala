package com.cosmoloj.sc.unit.simple.api

trait TransformedUnit extends AbstractUnit {

  def reference: AbstractUnit

  def toReference: UnitConverter

  override def toBase: UnitConverter = this.reference.toBase.concatenate(this.toReference)
}
