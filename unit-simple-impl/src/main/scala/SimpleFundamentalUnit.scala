package com.cosmoloj.sc.unit.simple.impl

import com.cosmoloj.sc.unit.simple.api.{FundamentalUnit, UnitConverter}

class SimpleFundamentalUnit extends SimpleUnit, FundamentalUnit {
  def toBase: UnitConverter = SimpleUnitConverter.identity
}
