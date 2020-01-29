package checkout

object Discounts {
  //We need the type alias to work with the partial function application and allow the offers to take different initial params
  type Offer = Seq[Item] => BigDecimal

  def percentageDiscount(discount: BigDecimal, itemInOffer: Item)(items: Seq[Item]): BigDecimal = {
    val applicableItems = items.filter {
      _ == itemInOffer
    }

    (applicableItems.size * (itemInOffer.value * discount)).setScale(2)
  }

  def buyItemDiscountAnother(discount: BigDecimal, itemQtyRequired: Int, itemRequired: Item, discountItem: Item)(items: Seq[Item]): BigDecimal = {
    val discountItemsRequired = items.filter(_ == itemRequired)
    val itemsToDiscount = items.filter(_ == discountItem)

    val amountToDiscount = discountItemsRequired.size / itemQtyRequired
    if (amountToDiscount == 0)
      BigDecimal(0)
    else
      percentageDiscount(discount, discountItem)(itemsToDiscount.take(amountToDiscount))
  }
}
