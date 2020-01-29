package checkout

import Discounts._

class Checkout(val itemsInSystem: Item*)(val currentOffers: Offer*) {

  def discountToApply(items: Seq[Item]): BigDecimal = currentOffers.map(a => a(items)).sum

  implicit class itemExtras(items: Seq[Item]) {
    def total: BigDecimal = items.map(_.value).sum
  }

  def parseArgs(arg: String): Option[Item] = validItems.get(arg).orElse {
    println(s"Invalid item $arg found")
    None
  }

  //Current known items of the system
  lazy val validItems: Map[String, Item] = itemsInSystem
    .map { item => (item.toString, item) }.toMap
}

object Checkout extends Checkout(Apples, Milk, Soup, Bread)(percentageDiscount(BigDecimal("0.10"), Apples)) with App {
  //Filter to only keep valid items
  val checkoutItems: Seq[Item] = args.flatMap(parseArgs)
  val subTotal = checkoutItems.total
  println(s"Subtotal: £$subTotal")

  //Calculate discount using offers
  val discount: BigDecimal = discountToApply(checkoutItems)

  println(s"Discounts applied: £$discount")
  println(s"Total price of items is: £${subTotal - discount}")
}
