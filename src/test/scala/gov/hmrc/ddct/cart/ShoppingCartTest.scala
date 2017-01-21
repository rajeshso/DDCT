package gov.hmrc.ddct.cart

import gov.hmrc.ddct.UnitSpec
import gov.hmrc.ddct.menu.{Coffee, Cola}

class ShoppingCartTest extends UnitSpec {

  "MyShoppingCart" should "calculate the item count" in {
    val myCart = new ShoppingCart()
    myCart.itemCount should be(0)
  }

  "MyShoppingCart" should "add Cola and Coffee" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
  }
  "MyShoppingCart" should "record the number of items as two" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
    myCart.itemCount should be(2)
  }
}