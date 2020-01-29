package checkout

sealed trait Item extends Product {
  val value: BigDecimal
}

case object Soup extends Item {
  override val value: BigDecimal = BigDecimal("0.65")
}

case object Bread extends Item {
  override val value: BigDecimal = BigDecimal("0.80")
}

case object Milk extends Item {
  override val value: BigDecimal = BigDecimal("1.3")
}

case object Apples extends Item {
  override val value: BigDecimal = BigDecimal("1")
}
