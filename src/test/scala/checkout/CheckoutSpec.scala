package checkout

import org.scalatest.FlatSpec
import Discounts._

class CheckoutSpec extends FlatSpec {

  private val items = Seq(Apples, Milk, Soup, Bread)

  "Checkout system" should "have no discount applied when there are no offers" in {
    val checkout = new Checkout(items: _*)()
    val result = checkout.discountToApply(items)
    assert(result === BigDecimal(0))
  }

  it should "apply both discounts and total 0.50" in {
    val checkout = new Checkout(items: _*)(percentageDiscount(BigDecimal("0.10"), Apples), percentageDiscount(BigDecimal("0.50"), Bread))
    val result = checkout.discountToApply(items)
    assert(result === BigDecimal("0.50"))
  }

}
