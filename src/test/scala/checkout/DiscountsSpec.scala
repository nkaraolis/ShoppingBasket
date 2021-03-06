package checkout

import org.scalatest.FlatSpec
import Discounts._

class DiscountsSpec extends FlatSpec {

  "Percentage discount" should "Apply 10% on 1 bag of apples and return 0.10 as discount" in {
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

  "Buy item discount another" should "Apply 50% off bread when paired with 2 tins of soup and return 0.40 as discount" in {
    val result = buyItemDiscountAnother(BigDecimal("0.50"), 2, Soup, Bread)(List(Soup, Bread, Soup))
    assert(result === BigDecimal("0.40"))
  }

  it should "Apply no discount off of bread when paired with 1 tin of soup" in {
    val result = buyItemDiscountAnother(BigDecimal("0.50"), 2, Soup, Bread)(List(Bread, Soup))
    assert(result === BigDecimal("0"))
  }

  it should "Apply no discount with a basket of just 2 tins of soup" in {
    val result = buyItemDiscountAnother(BigDecimal("0.50"), 2, Soup, Bread)(List(Soup, Soup))
    assert(result === BigDecimal("0"))
  }

}
