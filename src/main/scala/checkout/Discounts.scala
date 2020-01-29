package checkout

object Discounts {
  type Offer = Seq[Item] => BigDecimal
  def percentageDiscount(discount: BigDecimal, itemInOffer: Item)(items: Seq[Item]): BigDecimal = {
    val applicableItems = items.filter {
      _ == itemInOffer
    }

    applicableItems.size * (itemInOffer.value * discount)
  }
}
