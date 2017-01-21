package gov.hmrc.ddct.cart

import gov.hmrc.ddct.UnitSpec

class ShoppingCartTest extends UnitSpec {
  "MyShoppingCart" should "calculate the item count" in {
    val myCart = new ShoppingCart()
    myCart.itemCount should be(0)
  }
}