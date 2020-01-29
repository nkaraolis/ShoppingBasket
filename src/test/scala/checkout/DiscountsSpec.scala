package checkout

import org.scalatest.FlatSpec
import Discounts._

class DiscountsSpec extends FlatSpec {

  "Percentage discount on apples" should "Apply 10% on 1 bag of apples and return 0.10 as discount" in {
    val result = percentageDiscount(BigDecimal("0.10"), Apples)(Seq(Apples))
    assert(result === BigDecimal("0.10"))
  }

  it should "Apply 10% on 2 bags of apples and return 0.20 as discount" in {
    val result = percentageDiscount(BigDecimal("0.10"), Apples)(Seq(Apples))
    assert(result === BigDecimal("0.10"))
  }

  it should "Apply 10% on an empty basket and return 0 as discount" in {
    val result = percentageDiscount(BigDecimal("0.10"), Apples)(Nil)
    assert(result === BigDecimal("0"))
  }

  it should "Apply 10% on a basket with 1 bag of apples and 2 tins of soup and return 0.10 as discount" in {
    val result = percentageDiscount(BigDecimal("0.10"), Apples)(List(Apples, Soup, Soup))
    assert(result === BigDecimal("0.10"))
  }

}
